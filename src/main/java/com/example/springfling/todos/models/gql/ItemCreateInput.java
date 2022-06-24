package com.example.springfling.todos.models.gql;

import com.example.springfling.todos.models.doc.ItemModel;
import com.example.springfling.todos.models.doc.ListModel;

import java.util.Map;
import java.util.UUID;

public class ItemCreateInput {
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

    public ItemModel toItem(ListModel list, UUID id) {
        var item = new ItemModel();
        item.setId(id);
        item.setListId(list.getId());
        item.setName(getName());
        item.setPosition(getPosition());
        return item;
    }
}
