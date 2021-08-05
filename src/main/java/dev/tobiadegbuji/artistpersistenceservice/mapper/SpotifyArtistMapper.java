package dev.tobiadegbuji.artistpersistenceservice.mapper;

import dev.tobiadegbuji.artistpersistenceservice.domain.Artist;
import dev.tobiadegbuji.artistpersistenceservice.domain.Genre;
import dev.tobiadegbuji.artistpersistenceservice.domain.Image;
import dev.tobiadegbuji.artistpersistenceservice.domain.ImgTpye;
import dev.tobiadegbuji.artistpersistenceservice.dto.SpotifyArtist;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static dev.tobiadegbuji.artistpersistenceservice.utils.Constants.SPOTIFY_SERVICE_UNAVAILABLE;

@Component
@Log4j2
public class SpotifyArtistMapper {

    public Artist spotifyArtistToArtist(SpotifyArtist spotifyArtist) {


        Artist artist;

        try {

            //TODO: Add null checks

            artist = Artist.builder()
                    .genres(spotifyArtist.getGenres().stream()
                            .map(Genre::new)
                            .collect(Collectors.toSet()))
                    .spotifyId(spotifyArtist.getId())
                    .name(spotifyArtist.getName())
                    .images(spotifyArtist.getImages().stream()
                            .map(i -> new Image(i.getUrl(), ImgTpye.UNKNOWN))
                            .collect(Collectors.toSet()))
                    .build();

            Artist finalArtist = artist;
            log.debug(() -> "Mapped Artist: " + finalArtist);
        }
        catch (Exception e){
            // TODO: Make better implementation
            return Artist.builder()
                    .name(SPOTIFY_SERVICE_UNAVAILABLE)
                    .build();
        }

        return artist;

    }


}
