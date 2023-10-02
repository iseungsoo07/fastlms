package com.zerobase.fastlms.admin.banner.model;

import com.zerobase.fastlms.admin.banner.entity.Banner;
import com.zerobase.fastlms.admin.model.CommonParam;
import com.zerobase.fastlms.course.dto.CourseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerInput extends CommonParam {
    private Long id;
    private String bannerName;
    private String filename;
    private String urlFilename;
    private String linkAddr;
    private String openMethod;
    private String sort;
    private boolean publicYn;

    private String idList;
    private LocalDate regDate;
    private LocalDate updateDate;

    public static BannerInput of(Banner banner) {
        return BannerInput.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .filename(banner.getFilename())
                .urlFilename(banner.getUrlFilename())
                .linkAddr(banner.getLinkAddr())
                .openMethod(banner.getOpenMethod())
                .sort(banner.getSort())
                .publicYn(banner.isPublicYn())
                .build();
    }
}
