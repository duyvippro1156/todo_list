package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "tasks_project")
@Entity
public class TasksProject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;

    @JsonIgnore
	@ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Projects projects;

    @JsonIgnoreProperties(value = "task_id")
    @ManyToOne
    @JoinColumn(name="task_id", nullable=false)
    private Tasks tasks;

    @JsonIgnoreProperties(value = "user_id")
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Users users;

}
