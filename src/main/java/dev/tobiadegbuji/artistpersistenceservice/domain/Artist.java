package dev.tobiadegbuji.artistpersistenceservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist extends BaseEntity{

    @Id
    @GeneratedValue(generator = UUIDGenerator.UUID_GEN_STRATEGY)
    private UUID artistId;

    private String name;

    private String biography;

    private String twitterHandle;

    private String instagramHandle;

    private String spotifyHandle;

    @OneToMany
    private Set<Image> images;

    @OneToMany
    private Set<Album> discography;

    @OneToMany
    private Set<Episode> episodeCatalog;

    @OneToMany
    private Set<Genre> genres;


}
