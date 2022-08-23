package com.example.elastic.product.controller;

import com.example.elastic.product.param.ProductListParam;
import com.example.elastic.product.param.ProductParam;
import com.example.elastic.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductParam productParam){
        productService.createProduct(productParam);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/document")
    public ResponseEntity createProductDocument(){
        productService.createProductDocument();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getProduct(@RequestParam String name, Pageable pageable){
        return ResponseEntity.ok(productService.findByName(name, pageable));
    }

    @GetMapping("/list")
    public ResponseEntity getProductList(@RequestParam ProductListParam searchCondition, Pageable pageable){
        return ResponseEntity.ok(productService.getProductList(searchCondition, pageable));
    }
}
