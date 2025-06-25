package com.URLSHortner.ShortURL.Controller;

import com.URLSHortner.ShortURL.Entity.UrlInfo;
import com.URLSHortner.ShortURL.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlGenerateController {

    @Autowired
    UrlService urlService;

    @GetMapping("/generate")
    public UrlInfo generateId(@Param("url") String url ,@Param("userId") int userId)
    {
        System.out.println(url);
        return urlService.shortUrlService(url,userId);
    }
}
