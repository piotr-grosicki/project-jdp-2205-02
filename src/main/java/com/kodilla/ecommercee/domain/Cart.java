package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Cart_ID", unique = true)
    private long cartId;

    @Column(name = "Total_Value")
    @NotNull
    private long totalValue;

    @ManyToOne
    @JoinColumn(name = "ID_User")
    @NotNull
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Cart_Position",
            joinColumns = {@JoinColumn(name = "Cart_ID", referencedColumnName = "Cart_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Product_ID", referencedColumnName = "Product_ID")}
    )
    private List<Product> items;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private Order order;

}