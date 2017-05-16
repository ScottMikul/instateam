package org.teamtreehouse.instateam.model;

import javax.persistence.*;

/**
 * Created by scott on 5/11/2017.
 */
@Entity
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    @ManyToOne
    Role role;

    public Collaborator(){}

    public Collaborator(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
