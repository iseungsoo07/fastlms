package com.zerobase.fastlms.admin.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerDto {
    private Long id;
    private String bannerName;
    private String imagePath;
    private LocalDate regDate;

    long totalCount;
    long seq;
}
