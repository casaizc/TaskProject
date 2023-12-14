package com.project.taskProject.controller;

import com.project.taskProject.model.Task;
import com.project.taskProject.repository.TaskRepository;
import com.project.taskProject.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.mockito.Mockito;
import org.hamcrest.Matchers;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() throws Exception {

        Task task1 = new Task("Task 1", false);
        Task task2 = new Task("Task 2", false);
        List<Task> tasks = Arrays.asList(task1, task2);
        Mockito.when(taskService.findAll()).thenReturn(tasks);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is(task1.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Matchers.is(task2.getDescription())));
    }
}