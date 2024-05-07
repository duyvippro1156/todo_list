package com.example.demo.dto;

import java.time.LocalDateTime;

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
public class TaskProjectResponse {

    private Long id;

    private String task_name;
    private String completed;
    private int level;
    private int status;
    private String file;
    private LocalDateTime targetDate;


}
