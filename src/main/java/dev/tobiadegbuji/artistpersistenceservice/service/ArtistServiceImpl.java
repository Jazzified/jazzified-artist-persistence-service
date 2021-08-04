package dev.tobiadegbuji.artistpersistenceservice.service;

import dev.tobiadegbuji.artistpersistenceservice.domain.Artist;
import dev.tobiadegbuji.artistpersistenceservice.domain.Genre;
import dev.tobiadegbuji.artistpersistenceservice.domain.Image;
import dev.tobiadegbuji.artistpersistenceservice.dto.*;
import dev.tobiadegbuji.artistpersistenceservice.mapper.ArtistMapper;
import dev.tobiadegbuji.artistpersistenceservice.mapper.SpotifyArtistMapper;
import dev.tobiadegbuji.artistpersistenceservice.repo.ArtistRepo;
import dev.tobiadegbuji.artistpersistenceservice.repo.GenreRepo;
import dev.tobiadegbuji.artistpersistenceservice.repo.ImageRepo;
import dev.tobiadegbuji.artistpersistenceservice.service.rest.SpotifyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Log4j2
@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepo artistRepo;

    private final GenreRepo genreRepo;

    private final ImageRepo imageRepo;

    private final ArtistMapper artistMapper;

    private final SpotifyArtistMapper spotifyArtistMapper;

    private final SpotifyService spotifyService;

    @Override
    public ArtistDto retrieveArtistById(UUID artistId) {

        Artist artist = artistRepo.findById(artistId)
                .orElseThrow(RuntimeException::new);

        return artistMapper.artistToArtistDto(artist);
    }

    @Override
    public ArtistDto createArtist(ArtistDto artistDto) {

        log.debug(() -> "Incoming Artist: " + artistDto);

        Artist artist = artistMapper.artistDtoToArtist(artistDto);

        log.debug(() -> "Mapped Artist: " + artist);

        Artist savedArtist = artistRepo.save(artist);

        log.debug(() -> "Saved Artist: " + savedArtist);

        return artistMapper.artistToArtistDto(savedArtist);
    }


    @Override
    public ArtistDto createArtistViaSpotifyService(SpecificArtistSearchRequest searchRequest) {

        log.debug(() -> "Incoming SpecificArtistRequest: " + searchRequest);

        //Getting spotify search results
        SearchArtistResponse result = spotifyService.retrieveSpotifyArtist(searchRequest).block();

        if(result != null && result.getArtists() != null){
            List<SpotifyArtist> spotifyArtistList = result.getArtists().getItems();

            //Filtering search results using provided ID
            SpotifyArtist spotifyArtist = spotifyArtistList.stream()
                    .filter(artist -> artist.getId().equals(searchRequest.getArtistId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("User not found")); //TODO: IMPLEMENT PROPER EXCEPTION

            log.debug(() -> "Specific Artist found as: " + spotifyArtist);

            //Saving artist to DB
            Artist artist = spotifyArtistMapper.spotifyArtistToArtist(spotifyArtist);

            if(artist == null)
                throw new RuntimeException("Mapping Exception Occurred."); //TODO: IMPLEMENT PROPER EXCEPTION

            saveArtistTransientObjs(artist.getGenres(), artist.getImages());

            Artist savedArtist = artistRepo.save(artist);

            log.debug(() -> "Saved Artist: " + savedArtist);

            log.debug("EXITING createArtistViaSpotifyService Method");

            return artistMapper.artistToArtistDto(artist);
        }
        else
            throw new RuntimeException("Issue with Request"); //TODO: IMPLEMENT PROPER EXCEPTION

    }


    private void saveArtistTransientObjs(Set<Genre> genres, Set<Image> images){

        genres.stream()
                .forEach(genreRepo::save);

        images.stream()
                .forEach(imageRepo::save);

    }

}
