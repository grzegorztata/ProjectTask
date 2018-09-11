package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class TaskDto {
    private Long id;
    private String title;
    private String content;

    public TaskDto() {

    }
}
