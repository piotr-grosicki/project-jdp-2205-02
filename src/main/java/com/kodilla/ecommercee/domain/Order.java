package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ORDER")
public class Order {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Order_ID")
    private int OrderId;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Cart_ID")
    @NotNull
    @Column(name = "Cart_ID")
    private Cart cart;

    /*
    @NotNull
    @Column(name = "Status")
    private Status status;
    */

    @NotNull
    @Column(name = "Date")
    private LocalDate date = LocalDate.now();

}