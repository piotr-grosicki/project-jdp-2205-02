package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product_Group")
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    @Column(name = "Product_Group_Id")
    private Long id;

    @Column(name = "Name")
    private String name;


    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();
    
}
