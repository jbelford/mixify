package com.jbelford.mixify.services;

import com.google.gson.Gson;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {

    private final SpotifyApi spotify;

    @Autowired
    public SpotifyService(SpotifyApi spotify) {
        this.spotify = spotify;
    }

    public User parseUser(Object jsonObject) {
        Gson gson = new Gson();
        String json = gson.toJson(jsonObject);
        return new User.JsonUtil().createModelObject(json);
    }


}