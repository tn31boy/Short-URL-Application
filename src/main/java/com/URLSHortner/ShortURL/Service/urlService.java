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

    public UrlInfo shortUrl(String url,int id)
    {

        String shortUrl=base62Conversion(url);
    }

    public static String base62Conversion(String url)
    {
        String mapTo62="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String convertedUrl="";
        int i=0;
        int n= url.length();

        while(i<n)
        {
            int rem=
        }
    }



}
