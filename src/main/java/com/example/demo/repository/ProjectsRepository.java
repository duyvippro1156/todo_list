package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Projects;

import jakarta.transaction.Transactional;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    @Modifying
	@Transactional
	@Query(value = "UPDATE project p1 SET p1.status = 0 WHERE p1.id = ?1", nativeQuery = true)
	void softDelete (Long id);
    
	@Query(value = "SELECT p1.* FROM project p1 WHERE p1.status = 1", nativeQuery = true)
	List<Projects> findAllWithStatusActive();
}
