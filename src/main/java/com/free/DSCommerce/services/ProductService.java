package com.free.DSCommerce.services;

import com.free.DSCommerce.dto.ProductDTO;
import com.free.DSCommerce.entities.Product;
import com.free.DSCommerce.repositories.ProductRepository;
import com.free.DSCommerce.services.exceptions.DatabaseException;
import com.free.DSCommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findByid(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(product -> new ProductDTO(product));
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = productDTO.toEntity();
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        try {
            Product product = productRepository.getReferenceById(id);
            product = productDTO.toEntity(product);
            return new ProductDTO(productRepository.save(product));
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            productRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

}
