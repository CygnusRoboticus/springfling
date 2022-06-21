package com.example.springfling.models;

import java.util.Map;
import java.util.UUID;

public class ListModelGQLInput {
    public static ListModelGQLInput getCreateListRequest(Map map) {
        ListModelGQLInput input = new ListModelGQLInput();
        input.setName((String) map.get("name"));
        input.setPosition((Integer) map.get("position"));
        return input;
    }

    private String name;
    private Integer position;

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }

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
