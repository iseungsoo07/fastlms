package com.zerobase.fastlms.admin.banner.mapper;

import com.zerobase.fastlms.admin.banner.dto.BannerDto;
import com.zerobase.fastlms.admin.banner.model.BannerInput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {

    long selectListCount(BannerInput parameter);

    List<BannerDto> selectList(BannerInput parameter);

}
