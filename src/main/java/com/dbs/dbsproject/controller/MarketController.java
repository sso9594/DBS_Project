package com.dbs.dbsproject.controller;

import com.dbs.dbsproject.domain.Image;
import com.dbs.dbsproject.domain.Product;
import com.dbs.dbsproject.dto.JoinRequest;
import com.dbs.dbsproject.dto.LoginRequest;
import com.dbs.dbsproject.dto.ProductDto;
import com.dbs.dbsproject.repository.ImageRepository;
import com.dbs.dbsproject.service.ImageService;
import com.dbs.dbsproject.service.ProductService;
import com.dbs.dbsproject.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MarketController {

    private final UserService userService;
    private final ProductService productService;
    private final ImageService imageService;

    @GetMapping
    public String login(Model model){
        model.addAttribute("loginRequest", new LoginRequest());
        return "index";
    }

    @PostMapping(value = "/login")
    public String login(LoginRequest loginRequest) throws NoSuchAlgorithmException {
       return "redirect:/products";
    }

    @RequestMapping(value = "/signup")
    public String signup(Model model){
        model.addAttribute("joinRequest", new JoinRequest());
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signup(@Valid @ModelAttribute("joinRequest") JoinRequest joinRequest, BindingResult result) throws NoSuchAlgorithmException {
        if(result.hasErrors()){
            return "signup";
        }
        userService.join(joinRequest);
        return "redirect:/";
    }

    @GetMapping("/products")
    public String main(@RequestParam(name = "page", defaultValue = "0") int page, Model model){
        int pageSize = 10;
        PageRequest sortByPostid = PageRequest.of(page, pageSize, Sort.by("productid").descending());
        Page<Product> productPage = productService.findAllposts(sortByPostid);

        model.addAttribute("products", productPage.getContent().stream());
        model.addAttribute("currentPage", productPage.getNumber()+1);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "main";
    }

    @RequestMapping(value = "/save")
    public String save(Model model){
        model.addAttribute("ProductDto", new ProductDto());
        return "pagewrite";
    }

    @RequestMapping(value = "/detail/update")
    public String update(Model model, Integer id){
        ProductDto productDto = new ProductDto();
        productDto.setProductid(Long.valueOf(id));
        model.addAttribute("ProductDto", productDto);
        return "pageupdate";
    }

    @PostMapping(value = "/save")
    public String save(@Valid @ModelAttribute("ProductDto") ProductDto productDto, @RequestParam MultipartFile[] multipartFile, BindingResult result) throws NoSuchAlgorithmException, IOException {

        if(result.hasErrors()){
            return "signup";
        }

        Long productid = productService.save(productDto).getProductid();
        imageService.storeFiles(Arrays.stream(multipartFile).toList(), productid);
        return "redirect:/products";
    }

    @PostMapping(value = "/detail/update")
    public String update(@Valid @ModelAttribute("ProductDto") ProductDto productDto, @RequestParam MultipartFile[] multipartFile, BindingResult result) throws NoSuchAlgorithmException, IOException {

        if(result.hasErrors()){
            return "signup";
        }

        Long productid = productService.updateById(productDto).getProductid();
        imageService.storeFiles(Arrays.stream(multipartFile).toList(), productid);
        return "redirect:/detail/" + productDto.getProductid();
    }

    @GetMapping(value = "/detail/{id}")
    public String detailProduct(Model model, @PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        Product result = productService.findById(id);
        List<Image> imageResult = imageService.findAllByProductId(id);
        model.addAttribute("image", imageResult.stream());
        model.addAttribute("product", result);

        return "pagedetail";
    }

    @GetMapping(value = "/detail/delete")
    public String deleteProduct(Integer id){
        productService.deleteProduct(Long.valueOf(id));
        return "redirect:/products";
    }
}
