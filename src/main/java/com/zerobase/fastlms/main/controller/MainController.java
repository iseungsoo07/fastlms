package com.zerobase.fastlms.main.controller;


import com.zerobase.fastlms.admin.banner.model.BannerInput;
import com.zerobase.fastlms.admin.banner.service.BannerService;
import com.zerobase.fastlms.components.MailComponents;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MainController {

    private final MailComponents mailComponents;
    private final BannerService bannerService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model, BannerInput parameter) {
        String userAgent = RequestUtils.getUserAgent(request);
        String clientIp = RequestUtils.getClientIP(request);

        log.info(userAgent);
        log.info(clientIp);

        List<BannerInput> list = bannerService.getPublicBannerList(parameter);

        model.addAttribute("list", list);

        return "index";
    }


    @RequestMapping("/error/denied")
    public String errorDenied() {

        return "error/denied";
    }


}
