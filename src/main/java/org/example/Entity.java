package org.example;

// Entity är basklassen som både Animal och Crop ärver från

public class Entity {
    public int id;
    protected String name;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
            return "ID: " + id;
    }
}
