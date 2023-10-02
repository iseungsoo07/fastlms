package com.zerobase.fastlms.admin.banner.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bannerName;
    private String filename;
    private String urlFilename;
    private String linkAddr;
    private String openMethod;
    private String sort;
    private boolean publicYn;

    private LocalDate regDate;
    private LocalDate updateDate;
}
