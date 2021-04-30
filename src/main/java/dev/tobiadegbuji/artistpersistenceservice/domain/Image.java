package dev.tobiadegbuji.artistpersistenceservice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Image {

    @Id
    private UUID imgId;

    private String imgURL;

}
