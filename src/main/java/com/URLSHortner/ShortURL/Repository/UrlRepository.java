package com.URLSHortner.ShortURL.Repository;

import com.URLSHortner.ShortURL.Entity.UrlInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlInfo,Integer> {

}
