package org.teamtreehouse.instateam.controller;

public enum Status {
    NOT_STARTED("Not started"),
    ACTIVE("Active"),
    ARCHIVED("Archived");

    private String name;

    Status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
