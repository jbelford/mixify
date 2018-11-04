package com.jbelford.mixify.config;

import com.wrapper.spotify.SpotifyApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:secrets.properties")
public class AppConfigSpotify {

    @Value("${api.spotify.clientId}")
    private String clientId;

    @Value("${api.spotify.secret}")
    private String secret;

    @Bean
    public SpotifyApi spotifyApi() {
        return new SpotifyApi.Builder()
                .setClientId(this.clientId)
                .setClientSecret(this.secret)
                .build();
    }

}