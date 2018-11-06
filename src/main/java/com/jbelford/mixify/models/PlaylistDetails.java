package com.jbelford.mixify.models;

import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

import org.springframework.ui.ModelMap;

public class PlaylistDetails extends ModelMap {

    private PlaylistDetails setId(String id) {
        addAttribute("id", id);
        return this;
    }

    private PlaylistDetails setName(String name) {
        addAttribute("name", name);
        return this;
    }

    private PlaylistDetails setImage(String image) {
        addAttribute("image", image);
        return this;
    }

    public static PlaylistDetails mapFromSimplified(PlaylistSimplified playlist) {
        String image = playlist.getImages().length > 0
                ? playlist.getImages()[0].getUrl() : null;
        return new PlaylistDetails()
            .setId(playlist.getId())
            .setName(playlist.getName())
            .setImage(image);
    }


}