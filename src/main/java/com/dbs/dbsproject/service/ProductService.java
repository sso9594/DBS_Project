package com.dbs.dbsproject.service;

import com.dbs.dbsproject.domain.Image;
import com.dbs.dbsproject.domain.Product;
import com.dbs.dbsproject.dto.ProductDto;
import com.dbs.dbsproject.repository.ImageRepository;
import com.dbs.dbsproject.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public Product save(ProductDto productDto){
        return productRepository.save(productDto.toEntity());
    }

//    public List<ProductDto> findAllposts (PageRequest pageRequest){
//        List<Product> posts = productRepository.findAllByOrderByProduct_idDesc(pageRequest).getContent();
//        List<ProductDto> Allposts = new ArrayList<>();
//        for (Product post : posts){
//            ProductDto productDto = new ProductDto().fromEntity(post);
//            Allposts.add(productDto);
//        }
//        return Allposts;
//    }

    public Page<Product> findAllposts (PageRequest pageRequest){
        return productRepository.findAllByOrderByProductidDesc(pageRequest);
    }

    public Product findById(Long id) throws ChangeSetPersister.NotFoundException {
        return productRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public void deleteProduct(Long id){
        Optional<Product> post = productRepository.findById(id);
        if(!post.isPresent()){
            throw new NullPointerException("유효하지 않은 게시글");
        }
        productRepository.deleteById(id);
    }

    public Product updateById (ProductDto productDto){
        return save(productDto);
    }

    public List<String> getImageUrl(Product product){
        List<String> imageUrl = imageRepository.findAllByProductid(product.getProductid());
        System.out.println(imageUrl);
        return imageUrl;
    }

    public Product updateThumbnail (Long id, String url){
        Product target = productRepository.findById(id).get();
        target.setThumbnail(url);
        return productRepository.save(target);
    }
}
