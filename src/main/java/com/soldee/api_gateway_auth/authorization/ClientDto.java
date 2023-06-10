package com.soldee.api_gateway_auth.authorization;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class ClientDto {

    private final int id;
    private final String name;
    @JsonDeserialize(using = CustomRolesDeserializer.class)
    private List<GrantedAuthority> roles;

    public ClientDto(int id, String name, List<GrantedAuthority> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public List<GrantedAuthority> getRoles() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public void setRoles(List<GrantedAuthority> roles) {
        this.roles = roles;
    }
}


class CustomRolesDeserializer extends StdDeserializer<List<GrantedAuthority>> {
    public CustomRolesDeserializer(Class<List<GrantedAuthority>> t) {
        super(t);
    }

    @Override
    public List<GrantedAuthority> deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        try {
            JsonDeserializer<Object> deserializer = context.findRootValueDeserializer(context.constructType(List.class));
            List<String> list = (List<String>) deserializer.deserialize(parser, context);

            return list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        } catch (Exception e) {
            return null;
        }
    }
}
