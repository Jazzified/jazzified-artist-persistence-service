package dev.tobiadegbuji.artistpersistenceservice.service;

import dev.tobiadegbuji.artistpersistenceservice.domain.Artist;
import dev.tobiadegbuji.artistpersistenceservice.dto.ArtistDto;
import dev.tobiadegbuji.artistpersistenceservice.mapper.ArtistMapper;
import dev.tobiadegbuji.artistpersistenceservice.repo.ArtistRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
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

        log.debug(() -> "Incoming Artist: " + artistDto);

        Artist artist = artistMapper.artistDtoToArtist(artistDto);

        log.debug(() -> "Mapped Artist: " + artist);

        Artist savedArtist = artistRepo.save(artist);

        log.debug(() -> "Saved Artist: " + artistRepo.findById(savedArtist.getArtistId()).get());

        return artistMapper.artistToArtistDto(savedArtist);
    }
}
