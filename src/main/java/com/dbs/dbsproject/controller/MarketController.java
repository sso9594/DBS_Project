package com.dbs.dbsproject.controller;

import com.dbs.dbsproject.domain.Product;
import com.dbs.dbsproject.dto.JoinRequest;
import com.dbs.dbsproject.dto.LoginRequest;
import com.dbs.dbsproject.dto.ProductDto;
import com.dbs.dbsproject.service.ProductService;
import com.dbs.dbsproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MarketController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public String login(Model model){
        model.addAttribute("loginRequest", new LoginRequest());
        return "index";
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(LoginRequest loginRequest) throws NoSuchAlgorithmException {
       return userService.login(loginRequest);
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

        model.addAttribute("products", productPage.getContent());

        model.addAttribute("currentPage", productPage.getNumber()+1);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "main";
    }

    @RequestMapping(value = "/save")
    public String save(Model model){
        model.addAttribute("ProductDto", new ProductDto());
        return "pagewrite";
    }

    @PostMapping(value = "/save")
    public String save(@Valid @ModelAttribute("ProductDto") ProductDto productDto, BindingResult result) throws NoSuchAlgorithmException {
        if(result.hasErrors()){
            return "signup";
        }

        productService.save(productDto);
        return "redirect:/products";
    }
}
