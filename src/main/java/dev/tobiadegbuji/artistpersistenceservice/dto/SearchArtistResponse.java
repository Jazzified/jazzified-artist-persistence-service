package dev.tobiadegbuji.artistpersistenceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchArtistResponse {
    private SpotifyArtists artists;
}
