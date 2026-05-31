package com.sumit.sb_ecommercee.controller;

import com.sumit.sb_ecommercee.model.Product;
import com.sumit.sb_ecommercee.payload.ProductDTO;
import com.sumit.sb_ecommercee.payload.ProductResponse;
import com.sumit.sb_ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product,
                                                 @PathVariable Long categoryId ){

        ProductDTO productDTO = productService.addProduct(categoryId, product);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<ProductResponse> getAllProducts(){
        ProductResponse productResponse =  productService.getAllProducts();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
