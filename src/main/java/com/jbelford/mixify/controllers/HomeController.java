package com.jbelford.mixify.controllers;

import com.jbelford.mixify.config.oauth2.SpotifyOAuth2User;
import com.jbelford.mixify.models.HomeModel;
import com.jbelford.mixify.models.PlaylistDetails;
import com.jbelford.mixify.services.SpotifyService;

import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping
    public ModelAndView home(Authentication auth) throws Exception {
        var user = (SpotifyOAuth2User) auth.getPrincipal();

        List<PlaylistSimplified> playlists = this.spotifyService.getSimplifiedPlaylists();
        List<PlaylistDetails> playlistDetails = playlists.stream()
                .map(PlaylistDetails::mapFromSimplified)
                .collect(Collectors.toList());

        return new HomeModel("home")
                .setUsername(user.getName())
                .setPlaylists(playlistDetails);
    }
}