package com.URLSHortner.ShortURL.Service;

import com.URLSHortner.ShortURL.Entity.UrlInfo;
import com.URLSHortner.ShortURL.Repository.UrlRepository;
import com.URLSHortner.ShortURL.Strategies.SnowFlake.IdGenerationAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    IdGenerationAlgorithm idGenerationAlgorithm;

    @Autowired
    RedisService redisService;

    public UrlInfo shortUrlService(String url,int id)
    {
        long  urlId=idGenerationAlgorithm.generateID();
        String shortUrl=base62Conversion(urlId);
        UrlInfo shortUrlData=UrlInfo.builder().id(urlId).LongUrl(url).shortUrl(shortUrl).UserId(id).build();
        urlRepository.save(shortUrlData);
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
            int rem=(int)(id%base);
            convertedUrl=(""+mapTo62.charAt(rem))+convertedUrl;
            id/=62;

        }
        return convertedUrl;
    }


    public String getLongUrl(String shortUrl)
    {

        UrlInfo object=urlRepository.findByshortUrl(shortUrl);


        if(object==null)
            return "";

        System.out.println(redisService.save(shortUrl,object.getLongUrl()));
        return object.getLongUrl();
    }




}
