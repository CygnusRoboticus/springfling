package com.example.springfling.models;

import java.util.Map;
import java.util.UUID;

public class ItemModelGQLInput {
    public static ItemModelGQLInput getCreateItemRequest(Map map) {
        ItemModelGQLInput input = new ItemModelGQLInput();
        input.setListId((UUID) map.get("listId"));
        input.setName((String) map.get("name"));
        input.setPosition((Integer) map.get("position"));
        return input;
    }

    private UUID listId;
    private String name;
    private Integer position;

    public UUID getListId() { return listId; }
    public void setListId(UUID listId) {
        this.listId = listId;
    }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }

    public ItemModel toItem(UUID id) {
        var item = new ItemModel();
        item.setId(id);
        item.setListId(getListId());
        item.setName(getName());
        item.setPosition(getPosition());
        return item;
    }

    public void assign(ItemModel model) {
        model.setListId(getListId());
        model.setName(getName());
        model.setPosition(getPosition());
    }
}
