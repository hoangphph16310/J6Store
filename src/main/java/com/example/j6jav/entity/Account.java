package com.example.j6jav.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity  @Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Accounts")
public class Account {
    @Id
    private String userName;
    private String passWord;
    private String fullName;
    private String email;
    private String photo;
    @OneToMany(mappedBy = "account")
    List<Order> orders;
    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
    List<Authority> authorities;
}
