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
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    private boolean active;

    @Column(name = "Session_Token")
    private String sessionToken;

    @Column(name = "Session_Started")
    private LocalDateTime sessionStartTime;

    @Column(name = "activeCart_ID")
    private Long activeCartId;

    @OneToMany(
            targetEntity = Cart.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Cart> carts;

    public User(String userName, String phoneNumber, String email, String shippingAddress, boolean active,
                String sessionToken, LocalDateTime sessionStartTime, Long activeCartId) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.active = active;
        this.sessionToken = sessionToken;
        this.sessionStartTime = sessionStartTime;
        this.activeCartId = activeCartId;
        this.carts = new ArrayList<>();
    }
}
