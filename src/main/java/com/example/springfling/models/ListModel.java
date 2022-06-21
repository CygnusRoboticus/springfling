package com.example.springfling.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("lists")
public class ListModel {
    @Id
    public UUID id;
    public String name;
    public Integer position;

    public ListModel() {}

    public UUID getId() { return id; }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPosition() { return position; }
    public void setPosition(Integer position) {
        this.position = position;
    }
}
