package dev.tobiadegbuji.artistpersistenceservice.service;

import dev.tobiadegbuji.artistpersistenceservice.dto.ArtistDto;

import java.util.UUID;

public interface ArtistService {

    ArtistDto retrieveArtistById(UUID artistId);

    ArtistDto createArtist(ArtistDto artistDto);

}
