package org.teamtreehouse.instateam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamtreehouse.instateam.dao.CollaboratorDao;
import org.teamtreehouse.instateam.dao.ProjectDao;
import org.teamtreehouse.instateam.model.Collaborator;
import org.teamtreehouse.instateam.model.Project;

import java.util.List;

/**
 * Created by scott on 5/11/2017.
 */
@Service
public class CollaboratorServiceImpl implements CollaboratorService{
    @Autowired
    CollaboratorDao dao;

    @Override
    public Collaborator findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Collaborator> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Collaborator collab) {
        dao.save(collab);
    }

    @Override
    public void saveAll(List<Collaborator> collab) {
        dao.saveAll(collab);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }


}
