package com.test_springboot_mybatis.tttt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class TokenConfig {

    /**
     * 发行者
     */
    private String issuer;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * token 过期时间
     */
    private Duration accessTokenExpireTime;

    /**
     * pc端 刷新 token 过期时间
     */
    private Duration refreshTokenExpireTime;

    /**
     * app端 刷新 token 过期时间
     */
    private Duration refreshTokenExpireAppTime;



    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Duration getAccessTokenExpireTime() {
        return accessTokenExpireTime;
    }

    public void setAccessTokenExpireTime(Duration accessTokenExpireTime) {
        this.accessTokenExpireTime = accessTokenExpireTime;
    }

    public Duration getRefreshTokenExpireTime() {
        return refreshTokenExpireTime;
    }

    public void setRefreshTokenExpireTime(Duration refreshTokenExpireTime) {
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    public Duration getRefreshTokenExpireAppTime() {
        return refreshTokenExpireAppTime;
    }

    public void setRefreshTokenExpireAppTime(Duration refreshTokenExpireAppTime) {
        this.refreshTokenExpireAppTime = refreshTokenExpireAppTime;
    }
}
