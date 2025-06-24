package com.URLSHortner.ShortURL.Service;

import com.URLSHortner.ShortURL.Entity.UrlInfo;
import com.URLSHortner.ShortURL.Repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class urlService {

    @Autowired
    UrlRepository urlRepository;

    public UrlInfo shortUrlService(String url,int id)
    {

        String shortUrl=base62Conversion(1234455);
        UrlInfo shortUrlData=urlRepository.save(UrlInfo.builder().id(1).LongUrl(url).Shorturl(shortUrl).UserId(10).build());
        return shortUrlData;
    }

    public static String base62Conversion(long id)
    {
        String mapTo62="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String convertedUrl="";
        int i=0;
        long base=62;


        while(id!=0)
        {
            long rem=id%base;
            convertedUrl=(""+rem)+convertedUrl;
            id/=62;

        }
        return convertedUrl;
    }



}
