package org.teamtreehouse.instateam.controller;

import org.teamtreehouse.instateam.model.Collaborator;

import java.util.List;

/**
 * Created by scott on 5/20/2017.
 */
public class CollaboratorListWrapper {
    public List<Collaborator> collabs;

    public CollaboratorListWrapper(List<Collaborator> colabs) {
        this.collabs = colabs;
    }

    public List<Collaborator> getCollabs() {
        return collabs;
    }

    public void setCollabs(List<Collaborator> collabs) {
        this.collabs = collabs;
    }

    public CollaboratorListWrapper(){}
}
