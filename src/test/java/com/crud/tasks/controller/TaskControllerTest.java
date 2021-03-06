package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;


    private Gson gson = new Gson();

    @MockBean
    private DbService service;


    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        taskDtos.add(taskDto);

        List<Task> tasks = new ArrayList<>();
        Task task = new Task(1L, "title", "content");
        tasks.add(task);

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtos);

        //When Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$[0].content", is("content")));
    }


    @Test
    public void shouldGetTask() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        taskDtos.add(taskDto);

        List<Task> tasks = new ArrayList<>();
        Task task = new Task(1L, "title", "content");
        tasks.add(task);

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(service.getTask(task.getId())).thenReturn(Optional.ofNullable(task)); //using Optional

        //when Ten
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "title", "contetnt");
        taskDtos.add(taskDto);

        List<Task> tasks = new ArrayList<>();
        Task task = new Task(1L, "title", "content");
        tasks.add(task);

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When Then
        mockMvc.perform(post("/v1/task/createTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "title", "contetnt");
        taskDtos.add(taskDto);

        List<Task> tasks = new ArrayList<>();
        Task task = new Task(1L, "title", "content");
        tasks.add(task);

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When Then
        mockMvc.perform(put("/v1/task/updateTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));


    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        List<Task> tasks = new ArrayList<>();
        Task task = new Task(1L, "title", "content");
        tasks.add(task);

        when(service.getTask(task.getId())).thenReturn(Optional.ofNullable(task));

        //When Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}