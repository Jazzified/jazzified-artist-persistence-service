package dev.tobiadegbuji.artistpersistenceservice.mapper;

import dev.tobiadegbuji.artistpersistenceservice.domain.Artist;
import dev.tobiadegbuji.artistpersistenceservice.dto.ArtistDto;
import dev.tobiadegbuji.artistpersistenceservice.service.ArtistService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ArtistService.class)
public interface ArtistMapper {

    ArtistDto artistToArtistDto(Artist artist);

    Artist artistDtoToArtist(ArtistDto artistDto);

}
