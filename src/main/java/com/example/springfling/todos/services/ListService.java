package com.example.springfling.todos.services;

import com.example.springfling.todos.models.doc.ListModel;
import com.example.springfling.todos.repositories.ListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListService {
    private ListRepository listRepository;

    public List<ListModel> findAll() {
        return listRepository.findAll();
    }

    public Optional<ListModel> findById(UUID id) {
        return listRepository.findById(id);
    }

    public List<ListModel> findAllByPositions(List<Integer> positions) {
        return listRepository.findAllByPositions(positions);
    }

    public ListModel save(ListModel list) {
        return listRepository.save(list);
    }

    public void delete(ListModel list) {
        listRepository.delete(list);
    }
}
