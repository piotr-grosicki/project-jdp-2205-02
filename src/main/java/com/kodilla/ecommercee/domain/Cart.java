package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Cart_ID")
    private int cartId;

    @Column(name = "Total_Value")
    @NotNull
    private int totalValue;

    @ManyToOne
    @JoinColumn(name = "ID_User")
    @NotNull
    private User user;

    /*
    @OneToMany(
            targetEntity = CartPosition.class,
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private ArrayList<CartPosition> items;
     */

    @OneToOne
    @JoinColumn(name = "Order_ID")
    private Order order;

}