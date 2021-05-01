package dev.tobiadegbuji.artistpersistenceservice.service;

import dev.tobiadegbuji.artistpersistenceservice.domain.Artist;
import dev.tobiadegbuji.artistpersistenceservice.dto.ArtistDto;
import dev.tobiadegbuji.artistpersistenceservice.mapper.ArtistMapper;
import dev.tobiadegbuji.artistpersistenceservice.repo.ArtistRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepo artistRepo;

    private final ArtistMapper artistMapper;

    @Override
    public ArtistDto retrieveArtistById(UUID artistId) {

        Artist artist = artistRepo.findById(artistId)
                .orElseThrow(RuntimeException::new);

        return artistMapper.artistToArtistDto(artist);
    }

    @Override
    public ArtistDto createArtist(ArtistDto artistDto) {

        Artist artist = artistMapper.artistDtoToArtist(artistDto);

        Artist savedArtist = artistRepo.save(artist);

        return artistMapper.artistToArtistDto(savedArtist);
    }
}
