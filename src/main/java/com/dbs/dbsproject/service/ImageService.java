package com.dbs.dbsproject.service;

import com.dbs.dbsproject.domain.Image;
import com.dbs.dbsproject.dto.ImageDto;
import com.dbs.dbsproject.dto.ProductDto;
import com.dbs.dbsproject.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Value("${imageurl}")
    private String fileDir;

    public String getFullPath(String filename){
        return fileDir + filename;
    }

    public List<Image> storeFiles(List<MultipartFile> multipartFiles, ProductDto productDto) throws IOException{
        List<Image> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile, productDto));
            }
        }
        return storeFileResult;
    }

    public Image storeFile(MultipartFile multipartFile, ProductDto productDto) throws IOException{
        if(multipartFile.isEmpty()){
            return null;
        }

        String originFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        ImageDto image = ImageDto.builder()
                .productid(productDto.toEntity().getProductid())
                .originFileName(originFileName)
                .filePath(storeFileName)
                .build();
        return imageRepository.save(image.toEntity());
    }

    private String createStoreFileName(String originFileName){
        String uuid = UUID.randomUUID().toString();
        String extention = extractExt(originFileName);
        return uuid + "." + extention;
    }

    private String extractExt(String originFileName){
        int pos = originFileName.lastIndexOf(".");
        return originFileName.substring(pos + 1);
    }
}
