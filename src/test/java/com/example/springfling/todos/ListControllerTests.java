package com.example.springfling.todos;

import com.example.springfling.todos.repositories.ListRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

public class ListControllerTests {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private ListRepository listRepository;
}
