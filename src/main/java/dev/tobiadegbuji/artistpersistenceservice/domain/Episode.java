package dev.tobiadegbuji.artistpersistenceservice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID episodeId;

    private Long duration;

    private String summary;

    private String title;

    private Long number;



}
