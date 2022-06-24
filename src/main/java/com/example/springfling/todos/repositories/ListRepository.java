package com.example.springfling.todos.repositories;

import com.example.springfling.todos.models.doc.ListModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListRepository extends PagingAndSortingRepository<ListModel, UUID> {
    @Query(value = "{position: '?0'}")
    public Iterable<ListModel> findAllByPositions(List<Integer> positions, Pageable paging);
}
