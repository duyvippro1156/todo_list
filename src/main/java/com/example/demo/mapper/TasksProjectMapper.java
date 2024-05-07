package com.example.demo.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskProjectResponse;
import com.example.demo.model.TasksProject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TasksProjectMapper implements Function<TasksProject, TaskProjectResponse> {

    @Override
    public TaskProjectResponse apply(TasksProject t) {
        return TaskProjectResponse.builder()
                .id(t.getId())
                .level(t.getTasks().getLevel())
                .status(t.getTasks().getStatus())
                
                .task_name(t.getTasks().getTask_name())
                .build();
    }

}
