package com.free.DSCommerce.services;

import com.free.DSCommerce.dto.ProductDTO;
import com.free.DSCommerce.entities.Product;
import com.free.DSCommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findByid(Long id) {
        Product product = productRepository.findById(id).get();
        return new ProductDTO(product);
    }
}
