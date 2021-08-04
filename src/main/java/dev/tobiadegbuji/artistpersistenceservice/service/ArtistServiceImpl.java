package dev.tobiadegbuji.artistpersistenceservice.service;

import dev.tobiadegbuji.artistpersistenceservice.domain.Artist;
import dev.tobiadegbuji.artistpersistenceservice.domain.Genre;
import dev.tobiadegbuji.artistpersistenceservice.dto.ArtistDto;
import dev.tobiadegbuji.artistpersistenceservice.dto.SearchArtistResponse;
import dev.tobiadegbuji.artistpersistenceservice.dto.SpecificArtistSearchRequest;
import dev.tobiadegbuji.artistpersistenceservice.dto.SpotifyArtist;
import dev.tobiadegbuji.artistpersistenceservice.mapper.ArtistMapper;
import dev.tobiadegbuji.artistpersistenceservice.mapper.SpotifyArtistMapper;
import dev.tobiadegbuji.artistpersistenceservice.repo.ArtistRepo;
import dev.tobiadegbuji.artistpersistenceservice.repo.GenreRepo;
import dev.tobiadegbuji.artistpersistenceservice.repo.ImageRepo;
import dev.tobiadegbuji.artistpersistenceservice.service.rest.SpotifyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

        if (result != null && result.getArtists() != null) {
            List<SpotifyArtist> spotifyArtistList = result.getArtists().getItems();

            //Filtering search results using provided ID
            SpotifyArtist spotifyArtist = spotifyArtistList.stream()
                    .filter(artist -> artist.getId().equals(searchRequest.getArtistId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("User not found")); //TODO: IMPLEMENT PROPER EXCEPTION

            log.debug(() -> "Specific Artist found as: " + spotifyArtist);

            //Mapping spotify artist to artist
            Artist artist = spotifyArtistMapper.spotifyArtistToArtist(spotifyArtist);

            if (artist == null)
                throw new RuntimeException("Mapping Exception Occurred."); //TODO: IMPLEMENT PROPER EXCEPTION

            Artist artistTransientObjs = saveArtistTransientObjs(artist);

            Artist savedArtist = artistRepo.save(artistTransientObjs);

            log.debug(() -> "Saved Artist: " + savedArtist);

            log.debug("EXITING createArtistViaSpotifyService Method");

            return artistMapper.artistToArtistDto(artist);
        } else
            throw new RuntimeException("Issue with Request"); //TODO: IMPLEMENT PROPER EXCEPTION

    }


    private Artist saveArtistTransientObjs(Artist artist) {

        Set<Genre> generesToRemove = new HashSet<>();

        //If genre exist already, unique constraint violation is thrown. If thrown, add the genre to a removed list.
        Consumer<Genre> saveGenre = genre -> {

            try {
                genreRepo.save(genre);
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                if (Objects.requireNonNull(dataIntegrityViolationException.getMessage()).contains("genre")) {
                    generesToRemove.add(genre);
                    log.info("Duplicate Genre: " + genre + " was found.");
                }
                else
                    throw dataIntegrityViolationException;
            }
        };

        artist.getGenres()
                .forEach(saveGenre);

        //Filtering out genres that already exist
        Set<Genre> filteredGenreSet = artist.getGenres().
                stream().filter(genre -> !generesToRemove.contains(genre))
                .collect(Collectors.toSet());

        artist.setGenres(filteredGenreSet);

        artist.getImages()
                .forEach(imageRepo::save);

        return artist;

    }

}
