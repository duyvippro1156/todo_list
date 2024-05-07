package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.demo.model.TasksProject;
import com.example.demo.model.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProjectDto {
    private String project_name;
    private int status;
    private Long user_id_manager;
    private int member_role;

    private Users users;
    // private Set<TaskProjectResponse> tasksProjects;
    private List<TaskProjectResponse> tasksProjects = new ArrayList<>();
    // private Long taskProjectId;
}
