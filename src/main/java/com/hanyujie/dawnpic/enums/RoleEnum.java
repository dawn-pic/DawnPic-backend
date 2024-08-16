package com.hanyujie.dawnpic.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum RoleEnum {
    USER("ROLE_USER", "Common user"),
    ADMIN("ROLE_ADMIN", "Admin"),
    ;

    private final String role;
    private final String desc;

    RoleEnum(String role, String desc) {
        this.role = role;
        this.desc = desc;
    }

    public String getRole() {
        return role;
    }
}
