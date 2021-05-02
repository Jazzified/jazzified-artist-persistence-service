package dev.tobiadegbuji.artistpersistenceservice.dto;

import dev.tobiadegbuji.artistpersistenceservice.domain.Album;
import dev.tobiadegbuji.artistpersistenceservice.domain.Episode;
import dev.tobiadegbuji.artistpersistenceservice.domain.Genre;
import dev.tobiadegbuji.artistpersistenceservice.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistDto {

    @Null
    private UUID artistId;

    @NotNull
    private String name;

    @NotNull
    private String biography;

    private String twitterHandle;

    private String instagramHandle;

    private String spotifyHandle;

    private Set<Image> images;

    private Set<Album> discography;

    private Set<Episode> episodeCatalog;

    private Set<Genre> genres;

}
