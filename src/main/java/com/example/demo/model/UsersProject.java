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
@Table(name = "users_project")
@Entity
public class UsersProject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Projects project;


	@ManyToOne
    @JoinColumn(name="user_id_member", nullable=false)
    private Users users;

    private int member_role;

}
