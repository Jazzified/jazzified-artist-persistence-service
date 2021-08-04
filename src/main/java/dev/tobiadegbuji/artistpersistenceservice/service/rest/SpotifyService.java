package dev.tobiadegbuji.artistpersistenceservice.service.rest;

import dev.tobiadegbuji.artistpersistenceservice.dto.SearchArtistResponse;
import dev.tobiadegbuji.artistpersistenceservice.dto.SpecificArtistSearchRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

@Service
public class SpotifyService {

    public Mono<SearchArtistResponse> retrieveSpotifyArtist(SpecificArtistSearchRequest searchRequest){

        WebClient webClient = WebClient
                .builder()
                .baseUrl("http://localhost:8091/api/v1/jazzified/spotify/searchArtist")
                .build();

        Function<UriBuilder, URI> requestUri = uriBuilder -> uriBuilder
                .queryParam("query", searchRequest.getArtistName())
                //TODO: Client should not have option of setting a type.
                .queryParam("type", "artist")
                .queryParam("limit", searchRequest.getLimit())
                .build();

        return webClient.get()
                .uri(requestUri)
                .retrieve()
                .bodyToMono(SearchArtistResponse.class);
    }


}
