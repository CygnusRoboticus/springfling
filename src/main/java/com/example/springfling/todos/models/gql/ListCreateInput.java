package com.example.springfling.todos.models.gql;

import com.example.springfling.todos.models.doc.ListModel;

import java.util.Map;
import java.util.UUID;

public class ListCreateInput {
    public static ListCreateInput getCreateListRequest(Map map) {
        ListCreateInput input = new ListCreateInput();
        input.setName((String) map.get("name"));
        input.setPosition((Integer) map.get("position"));
        return input;
    }

    private String name;
    private Integer position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public ListModel toList(UUID id) {
        var list = new ListModel();
        list.setId(id);
        list.setName(getName());
        list.setPosition(getPosition());
        return list;
    }

    public void assign(ListModel model) {
        model.setName(getName());
        model.setPosition(getPosition());
    }
}
