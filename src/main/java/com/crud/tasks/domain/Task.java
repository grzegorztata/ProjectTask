package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class Task {
    private Long id;
    private String title;
    private String content;

    public Task() {

    }
}
