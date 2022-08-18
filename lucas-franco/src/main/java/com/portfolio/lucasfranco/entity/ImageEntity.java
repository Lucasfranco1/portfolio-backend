package com.portfolio.lucasfranco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;

@Entity
@Table(name = IMAGE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {
    @Id
    @GeneratedValue(generator = UUID)
    @GenericGenerator(name = UUID, strategy = UUID2)
    private String id;
    private String name;
    private String imageUrl;
    private String imageId;

    public ImageEntity(String name, String imageUrl, String imageId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }

}
