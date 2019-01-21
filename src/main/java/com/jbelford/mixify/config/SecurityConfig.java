package com.jbelford.mixify.config;

import com.jbelford.mixify.config.oauth2.SpotifyOAuth2UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/webjars/**", "/login", "/css/**", "/img/**", "js/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .oauth2Login()
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                    .userService(new SpotifyOAuth2UserService())
                    .and()
                .loginPage("/login");
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(@Autowired AppConfigSpotify spotifyApi) {
        var registrations = Arrays.asList(spotifyClientRegistration(spotifyApi));
        return new InMemoryClientRegistrationRepository(registrations);
    }

    private static final String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/login/oauth2/code/{registrationId}";

    private ClientRegistration spotifyClientRegistration(AppConfigSpotify spotifyApi) {
        return ClientRegistration.withRegistrationId(SpotifyConfigConstants.REGISTRATION_ID)
            .redirectUriTemplate(DEFAULT_LOGIN_REDIRECT_URL)
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .scope(SpotifyConfigConstants.Scopes.getAll())
            .authorizationUri(SpotifyConfigConstants.AUTHORIZATION_URL)
            .tokenUri(SpotifyConfigConstants.TOKEN_URL)
            .userInfoUri(SpotifyConfigConstants.USER_INFO)
            .userNameAttributeName("display_name")
            .clientName("Spotify")
            .clientId(spotifyApi.clientId)
            .clientSecret(spotifyApi.secret)
            .build();
    }

}