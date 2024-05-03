package com.example.demo.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectDto;
import com.example.demo.model.Projects;
import com.example.demo.model.Users;
import com.example.demo.model.UsersProject;
import com.example.demo.repository.ProjectsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UsersProjectRepository;
import com.example.demo.service.ProjectsService;

@Service
public class ProjectsServiceImpl implements ProjectsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private UsersProjectRepository usersProjectRepository;

    @Override
	public void createNewProject(ProjectDto projectDto) { 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() ->
                        new UsernameNotFoundException("not found  "+ auth.getName()));
		Projects projects = new Projects();
        UsersProject usersProject = new UsersProject();
        projects.setProject_name(projectDto.getProject_name());
        projects.setStatus(0);
        projects.setUsers(user);
        projectsRepository.save(projects);

        usersProject.setProject(projects);
        usersProject.setUsers(user);
        usersProject.setMember_role(0);
        usersProjectRepository.save(usersProject);
	} 
}
