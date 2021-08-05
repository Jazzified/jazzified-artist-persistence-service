package dev.tobiadegbuji.artistpersistenceservice.service.rest;

import dev.tobiadegbuji.artistpersistenceservice.dto.SearchArtistResponse;
import dev.tobiadegbuji.artistpersistenceservice.dto.SpecificArtistSearchRequest;
import dev.tobiadegbuji.artistpersistenceservice.dto.SpotifyArtists;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

@Service
public class SpotifyService {


    @CircuitBreaker(name = "retrieveSpotifyArtistCB", fallbackMethod = "retrieveSpotifyArtistFallback")
    @Retry(name = "retrieveSpotifyArtistRT", fallbackMethod = "retrieveSpotifyArtistFallback")
    @Bulkhead(name = "retrieveSpotifyArtistBH", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "retrieveSpotifyArtistFallback")
    public Mono<SearchArtistResponse> retrieveSpotifyArtist(SpecificArtistSearchRequest searchRequest) {

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

    /*
    Fallbacks provide a course of action when a resource has timed out or failed. If
    you find yourself using fallbacks to catch a timeout exception and then doing
    nothing more than logging the error, you should use a standard try...catch
    block around your service invocation instead: catch the exception and put the
    logging logic in the try...catch block. If you call out to
    another distributed service in your fallback service, you may need to wrap the
    fallback with a @CircuitBreaker.
     */
    private Mono<SearchArtistResponse> retrieveSpotifyArtistFallback(SpecificArtistSearchRequest searchRequest, Throwable throwable) {

        return Mono.just(SearchArtistResponse.builder()
                .artists(SpotifyArtists.builder()
                        .href("Sorry, no Spotify information currently available.")
                        .build())
                .build());
    }


}
