package com.dbs.dbsproject.domain;

import com.dbs.dbsproject.dto.ImageDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageid;

    private Long productid;

    private String originFileName;

    private String filePath;

    public Image update(ImageDto imageDto){
        this.productid = imageDto.getProductid();
        this.originFileName = imageDto.getOriginFileName();
        this.filePath = imageDto.getFilePath();
        return this;
    }
}
