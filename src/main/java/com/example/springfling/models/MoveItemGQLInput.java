package com.example.springfling.models;

import java.util.Map;
import java.util.UUID;

public class MoveItemGQLInput {
    public static MoveItemGQLInput getMoveItemRequest(Map map) {
        MoveItemGQLInput request = new MoveItemGQLInput();
        request.setListId(UUID.fromString((String) map.get("listId")));
        request.setPosition((Integer) map.get("position"));
        return request;
    }

    public UUID listId;
    public Integer position;

    public UUID getListId() { return listId; }
    public void setListId(UUID listId) {
        this.listId = listId;
    }
    public Integer getPosition() { return position; }
    public void setPosition(Integer position) {
        this.position = position;
    }
}
