package dev.tobiadegbuji.artistpersistenceservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Image extends BaseEntity{

    @Id
    @GeneratedValue(generator = UUIDGenerator.UUID_GEN_STRATEGY)
    private UUID imgId;

    private String imgURL;

    @Enumerated(value = EnumType.STRING)
    private ImgTpye imgTpye;

    public Image(String imgURL, ImgTpye imgTpye) {
        this.imgURL = imgURL;
        this.imgTpye = imgTpye;
    }

    public Image() {

    }
}
