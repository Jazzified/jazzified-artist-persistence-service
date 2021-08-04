package dev.tobiadegbuji.artistpersistenceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpotifyArtist {

    private SpotifyExternalUrl externalUrl;
    private SpotifyFollowers followers;
    private List<String> genres;
    private String href;
    private String id;
    private List<SpotifyArtistImage> images;
    private String name;
    private Integer popularity;
    private String type;
    private String uri;
}
