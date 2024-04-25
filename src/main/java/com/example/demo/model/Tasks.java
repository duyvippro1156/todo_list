package com.example.demo.model;



import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String completed; 

	private int level;
	private int is_delete;
	private int status;
	private String file;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate targetDate;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_task",
		inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private Set<Users> users;
} 

