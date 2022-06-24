package com.example.springfling.todos.services;

import com.example.springfling.todos.models.doc.ListModel;
import com.example.springfling.todos.repositories.ListRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListService {
    private ListRepository listRepository;

    public Iterable<ListModel> findAll(Pageable paging) {
        return listRepository.findAll(paging);
    }

    public Optional<ListModel> findById(UUID id) {
        return listRepository.findById(id);
    }

    public Iterable<ListModel> findAllByPositions(List<Integer> positions, Pageable paging) {
        return listRepository.findAllByPositions(positions, paging);
    }

    public ListModel save(ListModel list) {
        return listRepository.save(list);
    }

    public void delete(ListModel list) {
        listRepository.delete(list);
    }
}
