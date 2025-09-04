package com.free.DSCommerce.dto;

import com.free.DSCommerce.entities.Product;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
public class ProductDTO
{
    private Long id;

    @Size(min = 3, max = 80, message = "O nome deve ter entre 3 e 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;

    @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;

    @Positive(message = "O preço deve ser um valor positivo")
    private Double price;
    private String imgUrl;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO (Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
    }

    public Product toEntity() {
        return Product.builder()
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .imgUrl(this.imgUrl)
                .build();
    }

    public Product toEntity(Product product) {
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImgUrl(this.imgUrl);
        return product;
    }

}
