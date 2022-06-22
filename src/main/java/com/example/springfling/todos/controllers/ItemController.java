package com.example.springfling.todos.controllers;

import com.example.springfling.todos.models.doc.ItemModel;
import com.example.springfling.todos.models.gql.ItemCreateInput;
import com.example.springfling.todos.models.doc.ListModel;
import com.example.springfling.todos.models.gql.ListRef;
import com.example.springfling.todos.repositories.ItemRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.UUID;

@Controller
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @SchemaMapping(typeName = "List", field = "items")
    public Iterable<ItemModel> items(ListModel list) {
        return itemRepository.findAllByListId(list.getId());
    }

    @SchemaMapping(typeName = "ListMutation", field = "createItem")
    public Optional<ItemModel> createItem(@Argument("input") ItemCreateInput input) {
        var item = input.toItem(UUID.randomUUID());
        var saved = itemRepository.save(item);
        return Optional.of(saved);
    }

    @SchemaMapping(typeName = "Item", field = "list")
    public ListRef listRef(ItemModel item) {
        return new ListRef(item.getListId());
    }
}
