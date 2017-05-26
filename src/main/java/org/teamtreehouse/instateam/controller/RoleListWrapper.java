package org.teamtreehouse.instateam.controller;

import org.teamtreehouse.instateam.model.Role;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by scott on 5/23/2017.
 */
public class RoleListWrapper {
    @Valid
    private List<Role> roles;

    public RoleListWrapper(List<Role> roles) {
        this.roles = roles;
    }

    public RoleListWrapper() {
    }

    public List<Role> getRoles() {

        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
