package com.jbelford.mixify.models;

import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

import org.springframework.ui.ModelMap;

public class PlaylistModel extends ModelMap {

    private PlaylistModel setId(String id) {
        addAttribute("id", id);
        return this;
    }

    private PlaylistModel setName(String name) {
        addAttribute("name", name);
        return this;
    }

    private PlaylistModel setImage(String image) {
        addAttribute("image", image);
        return this;
    }

    public static PlaylistModel mapFromSimplified(PlaylistSimplified playlist) {
        String image = playlist.getImages().length > 0
                ? playlist.getImages()[0].getUrl() : null;
        return new PlaylistModel()
            .setId(playlist.getId())
            .setName(playlist.getName())
            .setImage(image);
    }


}