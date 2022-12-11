package com.example.j6jav.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity  @Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    @Temporal(TemporalType.DATE)
    private Date createDate = new Date();
    @ManyToOne
    @JoinColumn(name = "Username")
     Account account;
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;

}
