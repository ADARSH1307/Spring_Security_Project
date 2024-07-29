package com.ust.Ust_Ptojects.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String psnumber;//EMPID
    private String username;
    private String password;
    private String email;
    private String location;
    private boolean active;
    private String roles;
}
