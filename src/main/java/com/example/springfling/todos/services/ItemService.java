package com.example.springfling.todos.services;

import com.example.springfling.todos.models.doc.ItemModel;
import com.example.springfling.todos.repositories.ItemRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    public Iterable<ItemModel> findAll(Pageable paging) {
        return itemRepository.findAll(paging);
    }

    public Iterable<ItemModel> findAllByListId(UUID listId, Pageable paging) {
        return itemRepository.findAllByListId(listId, paging);
    }

    public Optional<ItemModel> findById(UUID id) {
        return itemRepository.findById(id);
    }

    public ItemModel save(ItemModel item) {
        return itemRepository.save(item);
    }

    public void delete(ItemModel item) {
        itemRepository.delete(item);
    }
}
