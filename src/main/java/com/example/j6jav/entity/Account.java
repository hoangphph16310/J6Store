package com.example.j6jav.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String passWord;
    @Column(name = "Fullname")
    private String fullName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Photo")
    private String photo;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
     List<Order> orders;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    List<Authority> authorities;

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", passWord='" + passWord + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", orders=" +
                ", authorities=" + authorities +
                '}';
    }
}
