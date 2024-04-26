package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tasks;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> { 
	@Query(value = "SELECT t1.* FROM tasks t1 WHERE t1.completed like '100%';", nativeQuery = true)
	List<Tasks> findByCompletedTrue();

	@Query(value = "SELECT t1.* FROM tasks t1 WHERE t1.completed not like '100%';", nativeQuery = true)
	List<Tasks> findByCompletedFalse();

	@Query(value = "SELECT t1.* FROM `tasks` t1 WHERE t1.target_date like %?1%", nativeQuery = true)
	Tasks findByTargetDate(String targetDate);

	List<Tasks> findAll(); 
	Tasks getById(Long id); 

	@Query(value = "SELECT t1.* FROM `tasks` t1 WHERE t1.task_name like %?1%", nativeQuery = true)
	List<Tasks> searchByTaskname(String keyWord);

	@Modifying
	@Transactional
	@Query(value = "UPDATE tasks t1 SET t1.is_delete = 1 WHERE t1.id = ?1", nativeQuery = true)
	void softDelete (Long id);

	@Query(value = "SELECT t1.* FROM tasks t1 WHERE t1.is_delete = 0", nativeQuery = true)
	List<Tasks> findAllWithoutDeleted();

} 

