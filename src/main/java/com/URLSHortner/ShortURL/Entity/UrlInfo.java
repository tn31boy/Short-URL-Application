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
    String LongUrl;

    @Column(nullable=false)
    String Shorturl;

    @Column(nullable=false)
    int UserId;
}
