package dev.tobiadegbuji.artistpersistenceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tobiadegbuji.artistpersistenceservice.dto.ArtistDto;
import dev.tobiadegbuji.artistpersistenceservice.service.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArtistController.class)
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistService artistService;

    private ArtistDto artistDto;

    private UUID id;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        objectMapper = new ObjectMapper();



        id = UUID.randomUUID();

        artistDto = ArtistDto.builder()
                .biography("Bio")
                .name("Michael Jackson")
                .spotifyHandle("spotify.com/michael-jackson")
                .build();

        //GIVEN
        BDDMockito.given(artistService.retrieveArtistById(ArgumentMatchers.any(UUID.class))).willReturn(artistDto);
        BDDMockito.given(artistService.createArtist(ArgumentMatchers.any(ArtistDto.class))).willReturn(artistDto);
    }

    @Test
    @DisplayName("retrieveArtist Test Happy Path")
    void retrieveArtist() throws Exception {

        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/artist/" + id))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(artistDto)));

    }

    @ParameterizedTest
    @DisplayName("retrieveArtist Test unable to parse UUID ")
    @NullSource
    @ValueSource(strings = {"-----------","123456789101112", "RANDOMARTISTID"})
    void retrieveArtist_unableToParseUUID(String input) throws Exception {

        BDDMockito.given(artistService.retrieveArtistById(ArgumentMatchers.any(UUID.class))).willReturn(artistDto);

        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/artist/" + input))
                .andDo(log())
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @DisplayName("retrieveArtist Test - Method Not Allowed for Endpoint ")
    @EmptySource
    void retrieveArtist_methodNotAllowed(String input) throws Exception {

        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/artist/" + input))
                .andDo(log())
                .andExpect(status().isMethodNotAllowed());
    }


    @Test
    @DisplayName("createArtist Test")
    void createArtist() throws Exception{

        //GIVEN
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/artist/")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artistDto))
                .contentType(MediaType.APPLICATION_JSON);

        //WHEN & THEN
        mockMvc.perform(request)
                .andDo(log())
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(artistDto)));
    }

}