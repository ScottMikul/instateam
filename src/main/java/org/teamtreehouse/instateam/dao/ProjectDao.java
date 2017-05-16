package org.teamtreehouse.instateam.dao;

import org.teamtreehouse.instateam.model.Project;
import org.teamtreehouse.instateam.model.Role;

import java.util.List;

/**
 * Created by scott on 5/13/2017.
 */
public interface ProjectDao {
    Project findById(int id);
    List<Project> findAll();
    void save(Project project);
    void delete(int id);
}
