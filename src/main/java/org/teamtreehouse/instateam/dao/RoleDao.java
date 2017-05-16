package org.teamtreehouse.instateam.dao;

import org.teamtreehouse.instateam.model.Role;

import java.util.List;

/**
 * Created by scott on 5/13/2017.
 */
public interface RoleDao {
    Role findById(int id);
    List<Role> findAll();
    void save(Role role);
    void delete(int id);

}
