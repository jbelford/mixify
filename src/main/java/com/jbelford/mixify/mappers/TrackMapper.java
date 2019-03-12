package com.jbelford.mixify.mappers;

import com.jbelford.mixify.models.TrackModel;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrackMapper {

    public static TrackModel map(PlaylistTrack track) {
        var artistNames = Stream.of(track.getTrack().getArtists())
                .map(ArtistSimplified::getName)
                .collect(Collectors.toList());

        return new TrackModel()
                .setName(track.getTrack().getName())
                .setArtists(artistNames);
    }

}