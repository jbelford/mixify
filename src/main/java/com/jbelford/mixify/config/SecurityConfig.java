package com.jbelford.mixify.config;

import com.jbelford.mixify.constants.SpotifyApiConstants;

import com.wrapper.spotify.SpotifyApi;

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
        http.authorizeRequests()
            // .antMatchers("/login")
            // .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2Login()
            .defaultSuccessUrl("/login");
            // .loginPage("/login");
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(@Autowired SpotifyApi spotifyApi) {
        var registrations = Arrays.asList(spotifyClientRegistration(spotifyApi));
        return new InMemoryClientRegistrationRepository(registrations);
    }

	private static final String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/login/oauth2/code/{registrationId}";

    private ClientRegistration spotifyClientRegistration(SpotifyApi spotifyApi) {
        return ClientRegistration.withRegistrationId(SpotifyApiConstants.REGISTRATION_ID)
            .redirectUriTemplate(DEFAULT_LOGIN_REDIRECT_URL)
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .scope(SpotifyApiConstants.Scopes.getAll())
            .authorizationUri(SpotifyApiConstants.AUTHORIZATION_URL)
            .tokenUri(SpotifyApiConstants.TOKEN_URL)
            .userInfoUri(SpotifyApiConstants.USER_INFO)
            .userNameAttributeName("display_name")
            .clientName("Spotify")
            .clientId(spotifyApi.getClientId())
            .clientSecret(spotifyApi.getClientSecret())
            .build();
    }

}