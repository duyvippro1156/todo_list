package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TasksDto {
	private Long id; 

	private String task_name; 
	private String completed; 
	private int level;
	private int is_delete;
	private int status;
	private String file;
	private LocalDateTime targetDate;

	private Long id_user;

}
