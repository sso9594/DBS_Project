package com.dbs.dbsproject.dto;

import com.dbs.dbsproject.domain.Image;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDto {
    private Long imageid;

    private Long productid;

    private String originFileName;

    private String filePath;

    public Image toEntity(){
        return Image.builder()
                .imageid(this.imageid)
                .productid(this.productid)
                .originFileName(this.originFileName)
                .filePath(this.filePath)
                .build();
    }
}
