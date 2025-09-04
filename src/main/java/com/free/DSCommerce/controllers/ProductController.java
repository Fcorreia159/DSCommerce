package com.free.DSCommerce.controllers;

import com.free.DSCommerce.dto.ProductDTO;
import com.free.DSCommerce.entities.Product;
import com.free.DSCommerce.repositories.ProductRepository;
import com.free.DSCommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity <ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findByid(id);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO> >findAll(Pageable pageable) {
        Page<ProductDTO> productDTOS = productService.findAll(pageable);
        return ResponseEntity.ok(productDTOS);

    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO productDTO) {
        productDTO = productService.insert(productDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(productDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(productDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity <ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        productDTO = productService.update(id, productDTO);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity <Void> update(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
