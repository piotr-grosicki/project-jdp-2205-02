package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Order_ID")
    private long OrderId;


    @OneToOne
    @JoinColumn(name = "Cart_ID")
    @NotNull
    private Cart cart;


    @NotNull
    @Column(name = "Status")
    private OrderStatus orderStatus;


    @NotNull
    @Column(name = "Date")
    private LocalDateTime date = LocalDateTime.now();

//    @PreRemove
//    void clear(){
//        this.cart  = null;
//    }

}