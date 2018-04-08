package jooq.converters;

import enums.Role;
import org.jooq.impl.EnumConverter;

public class RoleConverter extends EnumConverter<String, Role> {

    public RoleConverter() {
        super(String.class, Role.class);
    }

}