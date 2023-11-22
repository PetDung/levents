package com.nhom_6.duan_1.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column
    private String fullName;
    @Column
    private String email;
    @Column
    private Date dateOfBirth;
    @Column
    private String numberPhone;
    @Column
    private String account;
    @Column
    private String password;
    @Column
    private String status;
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonManagedReference
    private List<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Image image;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Bill> bills;

    @OneToOne
    private Address address;

    @OneToOne(mappedBy = "user")
    private ProductFavorite favoriteProducts;

    @ManyToMany(mappedBy = "users")
    private List<Voucher> vouchers;

    @OneToMany(mappedBy = "user")
    private List<ShiftAssignment> shiftAssignment;

    @OneToOne
    private Token token;
}
