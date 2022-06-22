package com.example.springfling.todos.controllers;

import com.example.springfling.todos.models.doc.ListModel;
import com.example.springfling.todos.models.gql.ListCreateInput;
import com.example.springfling.todos.models.gql.ListUpdateInput;
import com.example.springfling.todos.repositories.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.UUID;

@Controller
public class ListController {
    private final ListRepository listRepository;

    public ListController(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @QueryMapping(value = "lists")
    public Object listsQueries() { return new Object(); }

    @MutationMapping(value = "lists")
    public Object listsMutations() { return new Object(); }

    @SchemaMapping(typeName = "ListsQueries", field = "lists")
    public Iterable<ListModel> listsQuery() {
        return listRepository.findAll();
    }

    @SchemaMapping(typeName = "ListsQueries", field = "list")
    public Optional<ListModel> listQuery(@Argument("id") String id) {
        var uuid = UUID.fromString(id);
        return listRepository.findById(uuid);
    }

    @SchemaMapping(typeName = "ListsMutations", field = "create")
    public Optional<ListModel> listsMutateCreate(@Argument("input") ListCreateInput input) {
        var list = input.toList(UUID.randomUUID());
        var saved = listRepository.save(list);
        return Optional.of(saved);
    }

    @SchemaMapping(typeName = "ListsMutations", field = "list")
    public Optional<ListModel> listMutate(@Argument("id") String id) {
        var uuid = UUID.fromString(id);
        return listRepository.findById(uuid);
    }

    @SchemaMapping(typeName = "ListMutations", field = "update")
    public Optional<ListModel> listMutateUpdate(ListModel list, @Argument("input") ListUpdateInput input) {
        var saved = listRepository.save(list);
        return Optional.of(saved);
    }
}
