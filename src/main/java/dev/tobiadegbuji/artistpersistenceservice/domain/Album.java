package dev.tobiadegbuji.artistpersistenceservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Album extends BaseEntity{

    @Id
    private UUID albumId;

    @OneToMany
    private List<Song>  tracklist;

    private String description;

    private String yearRelease;

    private String coverImgUrl;

    //TODO: Replace with enum
    private String albumType;

}
