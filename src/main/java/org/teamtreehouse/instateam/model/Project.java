package org.teamtreehouse.instateam.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by scott on 5/11/2017.
 */
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @NotNull
    @Size(min = 3, max = 20)
    String name;

    @NotNull
    @Size(min = 3, max = 1024)
    String description;

    String status;

    @ManyToMany(cascade =CascadeType.ALL)
    List<Role> rolesneeded;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Collaborator> collaborators;

    public Project(){}

    Project(ProjectBuilder builder){
        this.id = builder.id;
        this.collaborators = builder.collaborators;
        this.name = builder.name;
        this.status = builder.status;
        this.description = builder.description;
        this.rolesneeded = builder.RolesNeeded;
    }

    public Project(int id, String name, String description, String status, List<Role> rolesneeded, List<Collaborator> collaborators) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.rolesneeded = rolesneeded;
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

    public List<Role> getRolesneeded() {
        return rolesneeded;
    }

    public void setRolesneeded(List<Role> rolesneeded) {
        this.rolesneeded = rolesneeded;
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
