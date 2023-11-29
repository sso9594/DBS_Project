package com.dbs.dbsproject.service;

import com.dbs.dbsproject.domain.Image;
import com.dbs.dbsproject.dto.ImageDto;
import com.dbs.dbsproject.dto.ProductDto;
import com.dbs.dbsproject.repository.ImageRepository;
import com.dbs.dbsproject.repository.ProductRepository;
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
    private final ProductService productService;

    public String getFullPath(String filename){
//        String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + File.separator + "main" + File.separator + "resources" + File.separator + "static" +  File.separator + "images" +File.separator;
        String filePath = "/Users/sinseung-yong/Desktop/dbsproject/images/";
        return filePath + filename;
    }

    public List<Image> storeFiles(List<MultipartFile> multipartFiles, Long id) throws IOException{
        List<Image> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile, id));
                if (multipartFiles.get(0) == multipartFile){
                    productService.updateThumbnail(id, storeFileResult.get(0).getFilePath());
                }
            }
        }
        return storeFileResult;
    }

    public Image storeFile(MultipartFile multipartFile, Long id) throws IOException{
        if(multipartFile.isEmpty()){
            return null;
        }

        String originFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originFileName);

        String fullPath = getFullPath(storeFileName);
        File directory = new File(fullPath).getParentFile();
        if(!directory.exists()){
            directory.mkdirs();
        }

        multipartFile.transferTo(new File(fullPath));
        ImageDto image = ImageDto.builder()
                .originFileName(originFileName)
                .filePath(storeFileName)
                .productid(id)
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

    public List<Image> findAllByProductId (Long id){
        return imageRepository.findAllByProductid(id);
    }
}
