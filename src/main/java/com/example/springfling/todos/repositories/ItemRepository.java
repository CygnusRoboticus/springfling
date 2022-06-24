package com.example.springfling.todos.repositories;

import com.example.springfling.todos.models.doc.ItemModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<ItemModel, UUID> {
    @Query(value = "{listId: '?0'}")
    public Iterable<ItemModel> findAllByListId(UUID listId, Pageable paging);
}
