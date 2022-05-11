package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID_User", unique = true)
    private Long userId;

    @NotNull
    @Column(name = "UserName")
    private String userName;

    @Column(name = "Telephone")
    private String phoneNumber;

    @NotNull
    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "Shipping_Address")
    private String shippingAddress;

    @NotNull
    @Column(name = "Active")
    private boolean isActive;

    @Column(name = "Session_Token")
    private String sessionToken;

    @Column(name = "Session_Started")
    private LocalDateTime sessionStartTime;

//usunąć zakomentowanie linijek po utworzeniu korespondującej encji Cart

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @Column(name = "ID_Cart")
//    private Cart activeCart;
//
//    @OneToMany(
//            targetEntity = Cart.class,
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<Cart> carts;
//
//    public User(String userName, String phoneNumber, String email, String shippingAddress, boolean isActive,
//                String sessionToken, LocalDateTime sessionStartTime, Cart activeCart, List<Cart> carts) {
//        this.userName = userName;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.shippingAddress = shippingAddress;
//        this.isActive = isActive;
//        this.sessionToken = sessionToken;
//        this.sessionStartTime = sessionStartTime;
//        this.activeCart = activeCart;
//        this.carts = carts;
//    }
}
