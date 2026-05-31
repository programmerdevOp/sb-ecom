package com.sumit.sb_ecommercee.service;

import com.sumit.sb_ecommercee.model.Product;
import com.sumit.sb_ecommercee.payload.ProductDTO;
import com.sumit.sb_ecommercee.payload.ProductResponse;

public interface ProductService {

    ProductDTO addProduct(Long categoryId, Product product);
    ProductResponse getAllProducts();
}
