package com.jbelford.mixify.services;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SpotifyService {

    @Autowired
    private SpotifyApi spotify;

    public List<PlaylistSimplified> getSimplifiedPlaylists() throws IOException {
        try {
            int offset = 0;
            Paging<PlaylistSimplified> paging;
            List<PlaylistSimplified> playlists = new ArrayList<>();

            do {
                paging = this.spotify.getListOfCurrentUsersPlaylists().limit(50).offset(offset).build().execute();
                playlists.addAll(Arrays.asList(paging.getItems()));
                offset += 50;
            } while (paging.getNext() != null);

            return playlists;
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        }
    }


}