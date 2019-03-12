package com.jbelford.mixify.controllers;

import com.jbelford.mixify.mappers.PlaylistMapper;
import com.jbelford.mixify.mappers.TrackMapper;
import com.jbelford.mixify.models.PlaylistModelView;
import com.jbelford.mixify.services.SpotifyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/playlists")
public class PlaylistsController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/{id}")
    public ModelAndView getPlaylist(@PathVariable("id") String playlistId) throws Exception {
        var playlistModel = PlaylistMapper.map(this.spotifyService.getPlaylist(playlistId));
        var tracksModel = this.spotifyService.getPlaylistTracks(playlistId).stream()
                .map(TrackMapper::map)
                .collect(Collectors.toList());

        return new PlaylistModelView()
                .setPlaylist(playlistModel)
                .setTracks(tracksModel);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Object updatePlaylist(@PathVariable("id") String playlistId) {
        return null;
    }

    @PostMapping
    @ResponseBody
    public Object createPlaylist() {
        return null;
    }

}