package org.teamtreehouse.instateam.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by scott on 5/11/2017.
 */
@Entity
public class Role {
    @Id
    int id;
    String name;

    public Role() {}

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
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
}
