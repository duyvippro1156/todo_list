package com.example.demo.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter; 

@Setter
@Getter
@Table(name = "Tasks")
@Entity
public class Tasks { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id; 

	private String task_name; 
	private boolean completed; 
} 

