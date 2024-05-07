package com.example.demo.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TasksDto;
import com.example.demo.model.Tasks;

@Service
public class TaskMapper implements Function<Tasks, TasksDto> {

    @Override
    public TasksDto apply(Tasks t) {
        return TasksDto.builder()
                .id(t.getId())
                .task_name(t.getTask_name())
                .build();
    }

}
