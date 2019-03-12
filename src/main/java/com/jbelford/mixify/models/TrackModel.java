package com.jbelford.mixify.models;

import org.springframework.ui.ModelMap;

import java.util.List;

public class TrackModel extends ModelMap {

    public TrackModel setName(String name) {
        addAttribute("name", name);
        return this;
    }

    public TrackModel setArtists(List<String> artists) {
        addAttribute("artist", artists);
        return this;
    }

}