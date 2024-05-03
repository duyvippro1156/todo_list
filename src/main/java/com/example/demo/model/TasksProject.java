package com.example.demo.model;

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

	@ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Projects projects;

    @ManyToOne
    @JoinColumn(name="task_id", nullable=false)
    private Tasks tasks;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Users users;

}
