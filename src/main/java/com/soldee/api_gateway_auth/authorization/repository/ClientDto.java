package com.soldee.api_gateway_auth.authorization.repository;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
@ToString
@Getter
public class ClientDto {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role")
    )
    private Set<RoleDto> roles;

}
