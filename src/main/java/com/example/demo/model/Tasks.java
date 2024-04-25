package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Set;

// import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime targetDate;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_task",
		inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private Set<Users> users;
} 

