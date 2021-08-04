package dev.tobiadegbuji.artistpersistenceservice.service;

import dev.tobiadegbuji.artistpersistenceservice.dto.ArtistDto;
import dev.tobiadegbuji.artistpersistenceservice.dto.SpecificArtistSearchRequest;

import java.util.UUID;

public interface ArtistService {

    ArtistDto retrieveArtistById(UUID artistId);

    ArtistDto createArtist(ArtistDto artistDto);

    ArtistDto createArtistViaSpotifyService(SpecificArtistSearchRequest specificArtistSearchRequest);

}
