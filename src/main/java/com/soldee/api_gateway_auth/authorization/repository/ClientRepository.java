package com.soldee.api_gateway_auth.authorization.repository;

import com.soldee.api_gateway_auth.authorization.DBAuthorizationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Set;

@ConditionalOnBean(DBAuthorizationService.class)
public interface ClientRepository extends JpaRepository<ClientDto, Long> {

    @Query(
            value = "SELECT clients.name AS name,client_roles.role AS role FROM clients INNER JOIN client_roles ON clients.id=client_roles.client_id WHERE clients.name =:name",
            nativeQuery = true
    )
    Set<ClientResult> findClientWithRolesByName(@Param("name") String name);

    public static interface ClientResult { String getName(); String getRole(); }
}
