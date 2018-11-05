package com.jbelford.mixify.controllers;

import com.jbelford.mixify.config.oauth2.SpotifyOAuth2User;
import com.jbelford.mixify.services.SpotifyService;

import com.wrapper.spotify.model_objects.specification.Image;
import com.wrapper.spotify.model_objects.specification.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Stream;

@Controller
public class HomeController {

    private final SpotifyService spotifyService;

    @Autowired
    HomeController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/")
    public String index(Model model, Authentication auth) throws Exception {
        var user = (SpotifyOAuth2User) auth.getPrincipal();
        User spotifyUser = user.getUser();
        model.addAttribute("userName", user.getName());
        String imageUrl = Stream.of(spotifyUser.getImages()).map(Image::getUrl).findFirst().orElse("");
        model.addAttribute("userImage", imageUrl);
        return "index";
    }

}