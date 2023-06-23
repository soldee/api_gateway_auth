package com.soldee.api_gateway_auth.authorization.repository;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleDto {

    @Id
    @Getter
    private String role;
}
