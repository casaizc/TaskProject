package com.project.taskProject.repository;

import java.util.List;

import com.project.taskProject.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(boolean status);

    List<Task> findByDescriptionContaining(String description);
}