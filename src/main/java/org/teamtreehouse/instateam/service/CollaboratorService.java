package org.teamtreehouse.instateam.service;

import org.teamtreehouse.instateam.model.Collaborator;
import org.teamtreehouse.instateam.model.Project;

import java.util.List;

/**
 * Created by scott on 5/11/2017.
 */

public interface CollaboratorService {
    Collaborator findById(int id);
    List<Collaborator> findAll();
    void save(Collaborator collaborator);
    void delete(int id);
}
