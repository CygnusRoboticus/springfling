package com.example.springfling.todos.repositories;

import com.example.springfling.todos.models.doc.ListModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListRepository extends MongoRepository<ListModel, UUID> {
    @Query(value = "{position:'?0'}")
    public List<ListModel> findAllByPositions(List<Integer> positions);
}
