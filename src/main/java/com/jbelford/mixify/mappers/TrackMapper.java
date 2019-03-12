package com.jbelford.mixify.mappers;

import com.jbelford.mixify.models.TrackModel;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrackMapper {

    public static TrackModel map(PlaylistTrack plTrack) {
        var track = plTrack.getTrack();
        var artistNames = Stream.of(track.getArtists())
                .map(ArtistSimplified::getName)
                .collect(Collectors.toList());

        return new TrackModel()
                .setName(track.getName())
                .setArtists(artistNames)
                .setIsLocal(plTrack.getIsLocal())
                .setIsPlayable(plTrack.getIsLocal() ? false : track.getIsPlayable());
    }

}