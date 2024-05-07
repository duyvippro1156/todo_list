package com.example.demo.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "Project")
@Entity
public class Projects {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id; 
	private String project_name; 
	private int status;

	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="user_id_manager", nullable=false)
    private Users users;

	@OneToMany(mappedBy="projects")
    private Set<TasksProject> tasksProjects;

	@JsonIgnore
	@OneToMany(mappedBy="project")
    private Set<UsersProject> usersProjects;

} 
