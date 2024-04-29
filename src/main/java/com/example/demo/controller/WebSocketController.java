package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.example.demo.model.Tasks;
import com.example.demo.repository.TasksRepository;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Component
public class WebSocketController {
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private TasksRepository tasksRepository;

    @Scheduled(fixedRate = 10000)
    @MessageMapping("/tasks")
    public void SendNotify(Principal user, @Header("Authorization") String token) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Tasks tasks = tasksRepository.findByTargetDate(formattedDateTime);
        if (tasks != null) {
            String message = tasks.getTask_name() + " has time out!";
            messagingTemplate.convertAndSend("/api/tasks", message);
        } else {
            String message = "Test notification";
            messagingTemplate.convertAndSend("/api/tasks", message);
        }
    }
}
