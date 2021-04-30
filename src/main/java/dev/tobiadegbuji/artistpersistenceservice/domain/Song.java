package dev.tobiadegbuji.artistpersistenceservice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Song {

    @Id
    private UUID songId;

    private String name;

    private String description;

    private Long duration;

}
