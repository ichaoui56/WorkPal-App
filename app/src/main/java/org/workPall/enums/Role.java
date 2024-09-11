package org.workPall.enums;

public enum Role {
    MEMBER,
    MANAGER,
    ADMIN;

    public static Role[] getNonAdminRoles() {
        return new Role[]{MEMBER, MANAGER};
    }
}
