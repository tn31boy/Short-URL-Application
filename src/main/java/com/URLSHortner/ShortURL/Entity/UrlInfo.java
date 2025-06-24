package com.URLSHortner.ShortURL.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class UrlInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable=false)
    String LongUrl;

    @Column(nullable=false)
    String Shorturl;

    @Column(nullable=false)
    int UserId;
}
