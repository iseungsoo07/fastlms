package com.zerobase.fastlms.admin.banner.service;

import com.zerobase.fastlms.admin.banner.dto.BannerDto;
import com.zerobase.fastlms.admin.banner.entity.Banner;
import com.zerobase.fastlms.admin.banner.mapper.BannerMapper;
import com.zerobase.fastlms.admin.banner.model.BannerInput;
import com.zerobase.fastlms.admin.banner.repository.BannerRepository;
import com.zerobase.fastlms.course.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    public List<BannerDto> list(BannerInput parameter) {
        List<Banner> banners = bannerRepository.findAll();

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return banners.stream()
                .map(banner -> BannerDto.builder()
                        .id(banner.getId())
                        .imagePath(banner.getUrlFilename())
                        .bannerName(banner.getBannerName())
                        .regDate(banner.getRegDate())
                        .build())
                .collect(Collectors.toList());
    }

    public boolean add(BannerInput parameter) {
        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .filename(parameter.getFilename())
                .urlFilename(parameter.getUrlFilename())
                .linkAddr(parameter.getLinkAddr())
                .openMethod(parameter.getOpenMethod())
                .sort(parameter.getSort())
                .publicYn(parameter.isPublicYn())
                .regDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .build();

        bannerRepository.save(banner);

        return true;
    }

    public boolean del(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    public BannerInput getById(long id) {
        return bannerRepository.findById(id).map(BannerInput::of).orElse(null);
    }

    public boolean update(BannerInput parameter) {
        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());

        if (optionalBanner.isPresent()) {
            Banner banner = optionalBanner.get();
            banner.setBannerName(parameter.getBannerName());
            banner.setFilename(parameter.getFilename());
            banner.setUrlFilename(parameter.getUrlFilename());
            banner.setLinkAddr(parameter.getLinkAddr());
            banner.setOpenMethod(parameter.getOpenMethod());
            banner.setSort(parameter.getSort());
            banner.setPublicYn(parameter.isPublicYn());
            banner.setRegDate(parameter.getRegDate());
            banner.setUpdateDate(LocalDate.now());


            bannerRepository.save(banner);
        }
        return true;
    }

    public List<BannerInput> getPublicBannerList(BannerInput parameter) {
        List<Banner> banners = bannerRepository.findAllByPublicYnOrderBySort(!parameter.isPublicYn());

        return banners.stream().map(banner -> BannerInput.builder()
                .id(banner.getId())
                .openMethod(banner.getOpenMethod())
                .linkAddr(banner.getLinkAddr())
                .urlFilename(banner.getUrlFilename())
                .bannerName(banner.getBannerName())
                .sort(banner.getSort())
                .regDate(banner.getRegDate())
                .build()).collect(Collectors.toList());
    }
}
