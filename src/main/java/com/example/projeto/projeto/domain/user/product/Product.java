package com.example.projeto.projeto.domain.user.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="tb_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product  {

 @Id
@GeneratedValue(strategy = GenerationType.UUID)
private String id;
private String name;
private Integer price;

public Product(ProductRequestDTO data){
    this.price = data.price();
    this.name = data.name();
}

}
