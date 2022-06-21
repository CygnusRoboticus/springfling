package com.example.springfling.models;

import java.util.Map;

public class MoveListGQLInput {
    public static MoveListGQLInput getMoveListRequest(Map map) {
        MoveListGQLInput request = new MoveListGQLInput();
        request.setPosition((Integer) map.get("position"));
        return request;
    }

    public Integer position;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
