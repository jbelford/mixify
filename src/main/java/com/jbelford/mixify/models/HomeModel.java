package com.jbelford.mixify.models;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class HomeModel extends ModelAndView {

    public HomeModel(String viewName) {
        super(viewName);
    }

    public HomeModel setUsername(String username) {
        addObject("username", username);
        return this;
    }

    public HomeModel setPlaylists(List<PlaylistDetails> playlists) {
        addObject("playlists", playlists);
        return this;
    }

}