package dev.tobiadegbuji.artistpersistenceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpotifyArtistImage {
    private Integer height;
    private String url;
    private Integer width;
}