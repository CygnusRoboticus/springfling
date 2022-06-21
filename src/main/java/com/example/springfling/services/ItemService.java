package com.example.springfling.services;

import com.example.springfling.models.ItemModel;
import com.example.springfling.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    public List<ItemModel> findAll() {
        return itemRepository.findAll();
    }

    public List<ItemModel> findAllByListId(UUID listId) {
        return itemRepository.findAllByListId(listId);
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
