package dev.tobiadegbuji.artistpersistenceservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Genre extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID genreId;

    @Column(unique = true)
    private String name;

    private String description;

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {

    }
}
