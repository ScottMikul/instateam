package org.teamtreehouse.instateam.service;


import org.teamtreehouse.instateam.model.Project;
import org.teamtreehouse.instateam.model.Role;

import java.util.List;

/**
 * Created by scott on 5/11/2017.
 */

public interface RoleService {
    Role findById(int id);
    List<Role> findAll();
    void save(Role role);
    void delete(int id);
}
