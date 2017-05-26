package org.teamtreehouse.instateam.dao;

import org.teamtreehouse.instateam.model.Collaborator;
import org.teamtreehouse.instateam.model.Project;
import org.teamtreehouse.instateam.model.Role;

import java.util.List;

/**
 * Created by scott on 5/13/2017.
 */
public interface CollaboratorDao {
    Collaborator findById(int id);
    List<Collaborator> findAll();
    void save(Collaborator collaborator);
    void saveAll(List<Collaborator> collab);
    void delete(int id);
}
