package com.example.demo.mapper;

import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.TaskProjectResponse;
import com.example.demo.model.Projects;
import com.example.demo.repository.TasksRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectMapper implements Function<Projects, ProjectDto> {
    private final TasksProjectMapper tasksProjectMapper;
    private final TasksRepository tasksRepository;

    @Override
    public ProjectDto apply(Projects t) {
        return ProjectDto.builder()
        .project_name(t.getProject_name())
        .status(t.getStatus())
        // .tasksProjects(tasksProjectMapper.apply(t.getTasksProjects()).stream().collect(Collectors.toList()))

        .taskProjectResponse(tasksProjectMapper.apply(t.getTasksProjects()).)
        .build();
    }

}
