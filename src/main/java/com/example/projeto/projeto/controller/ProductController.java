package com.example.projeto.projeto.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.projeto.domain.user.product.Product;
import com.example.projeto.projeto.domain.user.product.ProductRequestDTO;
import com.example.projeto.projeto.domain.user.product.ProductResponseDTO;
import com.example.projeto.projeto.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProductController {



    @Autowired
    private ProdutoRepository produtoRepository;


    @PostMapping("/save")
    
    public ResponseEntity<Product> salvar(@RequestBody @Valid ProductRequestDTO body){
        
    Product newProduct = new Product(body);
    produtoRepository.save(newProduct);
    return  ResponseEntity.ok().build();
    
    }

    @GetMapping("/getproduto")
    public ResponseEntity pegarLista(){
    List<ProductResponseDTO> produtoList = produtoRepository.findAll().stream().map(ProductResponseDTO::new).toList();
         return ResponseEntity.ok(produtoList);
    }
}
