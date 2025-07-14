package com.URLSHortner.ShortURL.Controller;

import com.URLSHortner.ShortURL.Entity.UrlInfo;
import com.URLSHortner.ShortURL.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping("/update/{shortUrl}")
    public ResponseEntity<?> updateUrl(@PathVariable("shortUrl") String shortUrl,@RequestParam("url") String url )
    {
        int isUpdate =urlService.urlUpdater(shortUrl,url);

        if(isUpdate==0)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("url Not present");

        return ResponseEntity.status(HttpStatus.FOUND).body("url changed Successfully");
    }


    @DeleteMapping("/delete/{shortUrl}")
    public ResponseEntity<?> deleteUrl(@PathVariable("shortUrl") String shortUrl)
    {
        int isDelete=urlService.deleteUrl(shortUrl);

        if(isDelete==0)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Url input is not Valid");

        return ResponseEntity.status(HttpStatus.FOUND).body("deleted");
    }

}
