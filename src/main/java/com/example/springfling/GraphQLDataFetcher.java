package com.example.springfling;

import com.example.springfling.models.*;
import com.example.springfling.services.ItemService;
import com.example.springfling.services.ListService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class GraphQLDataFetcher {
    @Autowired
    private ListService listService;
    @Autowired
    private ItemService itemService;

    public DataFetcher<List<ListModel>> getLists() {
        return dataFetchingEnvironment -> {
            List<ListModel> lists;
            if (dataFetchingEnvironment.getArguments().containsKey("positions")) {
                List<Integer> positions = dataFetchingEnvironment.getArgument("positions");
                lists = listService.findAllByPositions(positions);
            } else {
                lists = listService.findAll();
            }
            return lists;
        };
    }

    public DataFetcher<List<ItemModel>> getListItems() {
        return dataFetchingEnvironment -> {
            Optional<ListModel> listEntity = Optional.ofNullable(dataFetchingEnvironment.getSource());

            if (listEntity.isPresent()) {
                List<ItemModel> items = itemService.findAllByListId(listEntity.get().getId());
                if (dataFetchingEnvironment.getArguments().containsKey("positions")) {
                    List<Integer> positions = dataFetchingEnvironment.getArgument("positions");
                    items = items.stream()
                            .filter(item -> positions.contains(item.getPosition()))
                            .collect(Collectors.toList());
                }
                return items;
            } else {
                return null;
            }
        };
    }

    public DataFetcher<UUID> getItemListId() {
        return dataFetchingEnvironment -> {
            Optional<ItemModel> itemOpt = Optional.ofNullable(dataFetchingEnvironment.getSource());

            return itemOpt.map(item -> item.getListId()).orElse(new UUID(0, 0));
        };
    }

    public DataFetcher<Optional<ItemModel>> createItem() {
        return dataFetchingEnvironment -> {
            ItemModelGQLInput input = ItemModelGQLInput.getCreateItemRequest(dataFetchingEnvironment.getArgument("input"));
            Optional<ListModel> listOpt = listService.findById(input.getListId());

            return listOpt.map(list -> {
                var item = input.toItem(UUID.randomUUID());
                var saved = itemService.save(item);
                return Optional.of(saved);
            }).orElse(Optional.empty());
        };
    }

    public DataFetcher<ListModel> createList() {
        return dataFetchingEnvironment -> {
            ListModelGQLInput input = ListModelGQLInput.getCreateListRequest(dataFetchingEnvironment.getArgument("input"));
            ListModel list = input.toList(UUID.randomUUID());
            return this.listService.save(list);
        };
    }

    public DataFetcher<Optional<ListModel>> updateList() {
        return dataFetchingEnvironment -> {
            UUID id = UUID.fromString(dataFetchingEnvironment.getArgument("id"));
            ListModelGQLInput input = ListModelGQLInput.getCreateListRequest(dataFetchingEnvironment.getArgument("input"));

            Optional<ListModel> listOpt = this.listService.findById(id);
            return listOpt.map(list -> {
                input.assign(list);
                var saved = listService.save(list);
                return Optional.of(saved);
            }).orElse(Optional.empty());
        };
    }

    public DataFetcher<Optional<ItemModel>> moveItem() {
        return dataFetchingEnvironment -> {
            // UUID id = UUID.fromString(dataFetchingEnvironment.getArgument("id"));
            // Optional<ItemModel> item = itemService.findById(id);
            // MoveItemGQLInput moveItemRequest = MoveItemGQLInput.getMoveItemRequest(dataFetchingEnvironment.getArgument("input"));
            // Optional<ListModel> newList = listService.findById(moveItemRequest.getListId());
            // int newPosition = moveItemRequest.getPosition();
            // return itemService.swapPositions(item, newList, newPosition);

            return Optional.empty();
        };
    }

    public DataFetcher<Boolean> deleteItem() {
        return dataFetchingEnvironment -> {
            UUID id = UUID.fromString(dataFetchingEnvironment.getArgument("id"));
            Optional<ItemModel> itemOpt = itemService.findById(id);

            return itemOpt.map(item -> {
                itemService.delete(item);
                return true;
            }).orElse(false);
        };
    }

    public DataFetcher<Boolean> deleteList() {
        return dataFetchingEnvironment -> {
            UUID id = UUID.fromString(dataFetchingEnvironment.getArgument("id"));
            Optional<ListModel> listOpt = listService.findById(id);

            return listOpt.map(list -> {
                listService.delete(list);
                return true;
            }).orElse(false);
        };
    }
}