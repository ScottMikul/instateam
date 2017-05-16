package org.teamtreehouse.instateam.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by scott on 5/11/2017.
 */
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String description;
    String status;
    @ManyToMany
    List<Role> RolesNeeded;
    @ManyToMany
    List<Collaborator> collaborators;

    Project(){}

    Project(ProjectBuilder builder){
        this.id = builder.id;
        this.collaborators = builder.collaborators;
        this.name = builder.name;
        this.status = builder.status;
        this.description = builder.description;
        this.RolesNeeded = builder.RolesNeeded;
    }

    public Project(int id, String name, String description, String status, List<Role> rolesNeeded, List<Collaborator> collaborators) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        RolesNeeded = rolesNeeded;
        this.collaborators = collaborators;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Role> getRolesNeeded() {
        return RolesNeeded;
    }

    public void setRolesNeeded(List<Role> rolesNeeded) {
        RolesNeeded = rolesNeeded;
    }

    public static class ProjectBuilder{
        int id;
        String name;
        String description;
        String status;
        List<Role> RolesNeeded;
        List<Collaborator> collaborators;

        public ProjectBuilder withId(int id) {
            this.id = id;
            return this;
        }
        public ProjectBuilder withName(String name) {
            this.name = name;
            return this;
        }
        public ProjectBuilder withStatus(String status) {
            this.status = status;
            return this;
        }
        public ProjectBuilder withDescription(String desc) {
            this.description = desc;
            return this;
        }
        public ProjectBuilder withRoles(List<Role> roles) {
            this.RolesNeeded = roles;
            return this;
        }
        public ProjectBuilder withCollaborators(List<Collaborator> collaborators) {
            this.collaborators = collaborators;
            return this;
        }

        public Project build() {
            return new Project(this);
        }



    }

}
