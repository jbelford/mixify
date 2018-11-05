package com.jbelford.mixify.controllers;

import com.jbelford.mixify.config.oauth2.SpotifyOAuth2User;
import com.jbelford.mixify.services.SpotifyService;

import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/")
    public String index(Model model, Authentication auth) throws Exception {
        var user = (SpotifyOAuth2User) auth.getPrincipal();
        List<PlaylistSimplified> playlists = this.spotifyService.getSimplifiedPlaylists();
        model.addAttribute("userName", user.getName());
        model.addAttribute("playlists", playlists);
        return "index";
    }

}