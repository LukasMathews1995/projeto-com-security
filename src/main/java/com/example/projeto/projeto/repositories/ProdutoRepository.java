package com.example.projeto.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.projeto.domain.user.product.Product;

public interface ProdutoRepository extends JpaRepository<Product,String> {

    


}
