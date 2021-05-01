package dev.tobiadegbuji.artistpersistenceservice.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArtistPersistenceException extends RuntimeException{

    public ArtistPersistenceException(Throwable t) {
        super(t);
    }
}
