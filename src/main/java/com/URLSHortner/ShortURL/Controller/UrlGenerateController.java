package com.URLSHortner.ShortURL.Controller;

import com.URLSHortner.ShortURL.Entity.UrlInfo;
import com.URLSHortner.ShortURL.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


import java.net.URI;

@RestController
public class UrlGenerateController {

    @Autowired
    UrlService urlService;

    @GetMapping("/generate")
    public String generateId(@Param("url") String url ,@Param("userId") int userId)
    {
        System.out.println(url);
        UrlInfo object=urlService.shortUrlService(url,userId);
        return "http://localhost:8091/"+object.getShortUrl();
    }

    @GetMapping("/{ShortUrl}")
    public ResponseEntity<?> toLongUrl(@PathVariable("ShortUrl") String shortUrlBase62)
    {
        System.out.println(shortUrlBase62);
        String longUrl=urlService.getLongUrl(shortUrlBase62);

        if(longUrl.equals("expired"))
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("your url is expired");

        if(longUrl.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("url not found");

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longUrl)).build();
    }
}
