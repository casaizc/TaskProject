package com.project.taskProject.service;

import com.project.taskProject.model.Task;
import com.project.taskProject.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task update(long id) {
        Optional<Task> taskData = taskRepository.findById(id);
        if (taskData.isPresent()) {
            Task task = taskData.get();
            task.setStatus(true);
            return taskRepository.save(task);
        }
        return null;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public void deleteById(long theId) {
        taskRepository.deleteById(theId);
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public List<Task> findByStatus(boolean b) {
        return taskRepository.findByStatus(b);
    }

    public void deleteAll() {
        taskRepository.deleteAll();
    }

    public Optional<Task> findById(long id) {
       return taskRepository.findById(id);
    }
}
