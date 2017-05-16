package org.teamtreehouse.instateam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamtreehouse.instateam.dao.ProjectDao;
import org.teamtreehouse.instateam.model.Project;

import java.util.List;

/**
 * Created by scott on 5/11/2017.
 */

public interface ProjectService {
    Project findById(int id);
    List<Project> findAll();
    void save(Project project);
    void delete(int id);

}
