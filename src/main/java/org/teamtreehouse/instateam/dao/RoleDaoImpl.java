package org.teamtreehouse.instateam.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teamtreehouse.instateam.model.Role;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by scott on 5/13/2017.
 */
@Repository
public class RoleDaoImpl implements RoleDao{
    @Autowired
    private SessionFactory factory;

    @Override
    public Role findById(int id) {
        Session session = factory.openSession();
        Role role = session.get(Role.class,id);
        session.close();
        return role;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> findAll() {

        Session session = factory.openSession();
        session.beginTransaction();
        List <Role> res = session.createCriteria(Role.class).list();
        session.close();
        return res;
    }

    @Override
    public void save(Role role) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(role);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(int id) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(session.get(Role.class,id));
        session.getTransaction().commit();
        session.close();
    }


}
