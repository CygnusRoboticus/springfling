package com.example.springfling.todos.repositories;

import com.example.springfling.todos.models.doc.ItemModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends MongoRepository<ItemModel, UUID> {
    @Query(value = "{listId:'?0'}")
    public List<ItemModel> findAllByListId(UUID listId);
}
