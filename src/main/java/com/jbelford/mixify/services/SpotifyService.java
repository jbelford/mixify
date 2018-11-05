package com.jbelford.mixify.services;

import com.wrapper.spotify.SpotifyApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {

    private final SpotifyApi spotify;

    @Autowired
    public SpotifyService(SpotifyApi spotify) {
        this.spotify = spotify;
    }

    // public PlaylistSimplified[] getSimplifiedPlaylists(User user) {
    //     this.spotify.getPlaylist(playlist_id)
    // }


}