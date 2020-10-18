package com.clbee.appmaker.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "pb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String fullname;
    private String email;

    @Transient
    private String passwordConfirm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reg_dt")
    private Date regDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="chg_dt")
    private Date chgDt;

    @ManyToMany
    private Set<Role> roles;
}