package dev.tobiadegbuji.artistpersistenceservice.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Artist extends BaseEntity{

    @Id
    private UUID artistId;

    private String name;

    private String biography;

    private String twitterHandle;

    private String instagramHandle;

    private String spotifyHandle;

    @OneToMany
    private Set<Image> bannerImages;

    @OneToMany
    private Set<Image> profileImages;

    @OneToMany
    private Set<Image> randomImages;

    @OneToMany
    private Set<Album> discography;

    @OneToMany
    private Set<Episode> episodeCatalog;

    @OneToMany
    private Set<Genre> genres;


}
