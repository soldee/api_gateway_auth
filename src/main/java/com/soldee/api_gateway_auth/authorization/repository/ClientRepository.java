package com.soldee.api_gateway_auth.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ClientRepository extends JpaRepository<ClientDto, Long> {

    @Query("SELECT c FROM ClientDto c JOIN c.roles r WHERE c.name LIKE %?1%")
    List<ClientDto> findClientWithRolesByName(String name);
}
