package com.URLSHortner.ShortURL.Repository;

import com.URLSHortner.ShortURL.Entity.UrlInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlInfo,Long> {

    UrlInfo findByshortUrl(String shortUrl);

    int deleteByshortUrl(String shortUrl);

    @Modifying
    @Query("Update UrlInfo u set u.longUrl= :url  where u.shortUrl=:shortUrl")
    int updateUrl(@Param("shortUrl") String shortUrl, @Param("url") String url);

}
