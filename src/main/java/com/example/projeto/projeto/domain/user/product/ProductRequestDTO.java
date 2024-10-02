package com.example.projeto.projeto.domain.user.product;

public record ProductRequestDTO (String id,String name, Integer price){
    public ProductRequestDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}
