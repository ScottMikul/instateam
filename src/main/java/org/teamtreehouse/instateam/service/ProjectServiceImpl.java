package org.teamtreehouse.instateam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamtreehouse.instateam.dao.ProjectDao;
import org.teamtreehouse.instateam.model.Project;

import java.util.List;

/**
 * Created by scott on 5/11/2017.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectDao dao;
    @Override
    public Project findById(int id) {
       return dao.findById(id);
    }

    @Override
    public List<Project> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Project project) {
        dao.save(project);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
