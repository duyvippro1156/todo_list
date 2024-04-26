package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
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

    @Scheduled(fixedRate = 1000)
    @MessageMapping("/private")
    public void callAPI() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]");
        String formattedDateTime = now.format(formatter);

        Tasks tasks = tasksRepository.findByTargetDate(formattedDateTime);
        if (tasks != null) {
            String message = tasks.getTask_name() + "has time out!";
            messagingTemplate.convertAndSend("/topic/public", message);
        }
    }
}
