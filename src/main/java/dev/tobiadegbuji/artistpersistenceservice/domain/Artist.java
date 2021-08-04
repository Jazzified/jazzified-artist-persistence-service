package dev.tobiadegbuji.artistpersistenceservice.domain;


import lombok.*;
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
@Builder
public class Artist extends BaseEntity{

    @Id
    @GeneratedValue(generator = UUIDGenerator.UUID_GEN_STRATEGY)
    private UUID artistId;

    private String name;

    private String biography;

    private String twitterHandle;

    private String instagramHandle;

    private String spotifyHandle;

    private String spotifyId;

    @OneToMany
    private Set<Image> images;

    @OneToMany
    private Set<Album> discography;

    @OneToMany
    private Set<Episode> episodeCatalog;

    @OneToMany
    private Set<Genre> genres;


}
