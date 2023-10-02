package com.zerobase.fastlms.admin.banner.service;

import com.zerobase.fastlms.admin.banner.dto.BannerDto;
import com.zerobase.fastlms.admin.banner.model.BannerInput;

import java.util.List;

public interface BannerService {
    boolean add(BannerInput parameter);
    boolean del(String idList);

    BannerInput getById(long id);

    List<BannerInput> getPublicBannerList(BannerInput parameter);

    List<BannerDto> list(BannerInput parameter);

    boolean update(BannerInput parameter);

}
