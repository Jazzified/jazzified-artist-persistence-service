package dev.tobiadegbuji.artistpersistenceservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Image extends BaseEntity{

    @Id
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
