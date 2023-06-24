package com.soldee.api_gateway_auth.authorization.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleDto {

    @Id
    private String role;
}
