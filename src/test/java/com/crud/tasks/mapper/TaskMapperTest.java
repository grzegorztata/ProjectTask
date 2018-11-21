package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals("title", task.getTitle());
        assertEquals("content", task.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L, "title1", "content1");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals("title1", taskDto.getTitle());
        assertEquals("content1", taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "title1", "content1"));
        tasks.add(new Task(1L, "title2", "content2"));
        tasks.add(new Task(1L, "title3", "content3"));
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(3, tasks.size());
    }
}