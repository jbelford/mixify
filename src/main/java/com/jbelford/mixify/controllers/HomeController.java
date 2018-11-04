package com.jbelford.mixify.controllers;

import com.jbelford.mixify.services.SpotifyService;

import com.wrapper.spotify.model_objects.specification.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final SpotifyService spotifyService;

    @Autowired
    HomeController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/")
    public String index(Model model, Authentication auth) throws Exception {
        OAuth2User user = (OAuth2User) auth.getPrincipal();
        User spotifyUser = this.spotifyService.parseUser(user.getAttributes());
        model.addAttribute("userName", user.getName());
        String imageUrl = spotifyUser.getImages().length > 0 ? spotifyUser.getImages()[0].getUrl() : "";
        model.addAttribute("userImage", imageUrl);
        return "index";
    }

}