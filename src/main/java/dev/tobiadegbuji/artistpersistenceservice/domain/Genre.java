package dev.tobiadegbuji.artistpersistenceservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Genre extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID genreId;

    private String name;

    private String description;

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {

    }
}
