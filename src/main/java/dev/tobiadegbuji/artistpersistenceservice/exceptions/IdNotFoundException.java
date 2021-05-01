package dev.tobiadegbuji.artistpersistenceservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Data
public class IdNotFoundException extends ArtistPersistenceException{

    private UUID id ;

}
