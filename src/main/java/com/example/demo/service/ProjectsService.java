package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProjectDto;
import com.example.demo.model.Projects;


public interface ProjectsService {
    public void createNewProject(ProjectDto projectDto);

    public boolean checkProjectAuthor(Long id);

    public List<Projects> getAllProject();
	
	public Projects findProjectById(Long id);

	public void deActiveProject(Long id);
	
	public Projects updateProject(ProjectDto projectDto, Long id);

}
