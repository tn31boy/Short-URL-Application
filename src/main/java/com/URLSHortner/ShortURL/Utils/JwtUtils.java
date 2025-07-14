package com.URLSHortner.ShortURL.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class JwtUtils {

    private final String privateKey="secret";


    public String isTokenValid(String token)
    {
        try{
            Claims claims =getClaims(token);

            if(isExpired(claims.getExpiration()))
                    return null;

            return claims.getSubject();

        }
        catch(Exception e) {
            System.out.println(e);
        }

    }

    public Claims getClaims(String token)
    {
        return Jwts.parser().setSigningKey(privateKey.getBytes()).parseClaimsJws(token).getBody();
    }

    public boolean isExpired(Date time)
    {
        return time.before(new Date());
    }

}
