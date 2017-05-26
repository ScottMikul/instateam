package org.teamtreehouse.instateam.dao;


import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teamtreehouse.instateam.model.Collaborator;

import java.util.List;

/**
 * Created by scott on 5/13/2017.
 */
@Repository
public class CollaboratorDaoImpl implements CollaboratorDao {
    @Autowired
    private SessionFactory factory;

    @Override
    public Collaborator findById(int id) {
        Session session = factory.openSession();
        Collaborator collaborator = session.get(Collaborator.class,id);
        session.close();
        return collaborator;
    }

    @Override
    public List<Collaborator> findAll() {
        Session session = factory.openSession();
        session.beginTransaction();
        List <Collaborator> res = session.createCriteria(Collaborator.class).list();
        for(Collaborator collab: res){
            Hibernate.initialize(collab);
        }
        session.close();
        return res;
    }

    @Override
    public void save(Collaborator collaborator) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(collaborator);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Collaborator> collaborator) {
        Session session = factory.openSession();
        session.beginTransaction();
        for(Collaborator c: collaborator) session.saveOrUpdate(c);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(session.get(Collaborator.class,id));
        session.getTransaction().commit();
        session.close();
    }
}
