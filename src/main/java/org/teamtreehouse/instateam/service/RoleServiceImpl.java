package org.teamtreehouse.instateam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamtreehouse.instateam.dao.RoleDao;
import org.teamtreehouse.instateam.model.Project;
import org.teamtreehouse.instateam.model.Role;

import java.util.List;

/**
 * Created by scott on 5/11/2017.
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleDao dao;

    @Override
    public Role findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Role role) {
        dao.save(role);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
