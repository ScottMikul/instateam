package org.teamtreehouse.instateam.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teamtreehouse.instateam.model.Project;


import java.util.List;

/**
 * Created by scott on 5/13/2017.
 */
@Repository
public class ProjectDaoImpl implements ProjectDao {
    @Autowired
    private SessionFactory factory;

    @Override
    public Project findById(int id) {
        Session session = factory.openSession();
        Project project = session.get(Project.class,id);
        Hibernate.initialize(project.getCollaborators());
        Hibernate.initialize(project.getRolesNeeded());
        session.close();
        return project;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Project> findAll() {

        Session session = factory.openSession();
        session.beginTransaction();
        List <Project> res = session.createCriteria(Project.class).list();
        session.close();
        return res;
    }

    @Override
    public void save(Project project) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(project);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(int id) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(session.get(Project.class,id));
        session.getTransaction().commit();
        session.close();
    }
}
