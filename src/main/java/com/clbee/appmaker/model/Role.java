package com.clbee.appmaker.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "pb_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long id;

    @Column(name="name")
    private String username;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}