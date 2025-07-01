package com.URLSHortner.ShortURL.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "urlInfo")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlInfo {

    @Id
    long id;

    @Column(nullable=false)
    String longUrl;

    @Column(nullable=false,unique = true)
    String shortUrl;

    @Column(nullable=false)
    int UserId;

    long expiration;
}
