package com.project.taskProject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.taskProject.model.Task;
import com.project.taskProject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.taskProject.repository.TaskRepository;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //endpoints
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            List<Task> task = new ArrayList<Task>();

                task.addAll(taskService.findAll());

            if (task.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") long id) {
        Optional<Task> taskData = taskService.findById(id);

        return taskData.map(task -> new ResponseEntity<>(task, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody(required = false) Task task) {
        try {
            Task _task = taskRepository.save(new Task(task.getDescription(), false));

            return new ResponseEntity<>(_task, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
        Optional<Task> taskData = taskRepository.findById(id);

        if (taskData.isPresent()) {
            Task _task = taskData.get();
            _task.setDescription(task.getDescription());
            _task.setStatus(task.getStatus());
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
        try {
            taskService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<HttpStatus> deleteAllStatus() {
        try {
            taskService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/tasks/status")
    public ResponseEntity<List<Task>> findByStatus() {
        try {
            List<Task> task = taskService.findByStatus(true);

            if (task.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodos para la interfaz de usuario
    @GetMapping("/list")
    public ModelAndView sayHello(Model theModel) {
        Task _task = new Task();
        List<Task> task = taskService.findAll();
        ModelAndView mav = new ModelAndView("tasks/list-tasks");
        mav.addObject("theTask", task);
        mav.addObject("newTask", _task);
        return mav;
    }
    @PostMapping("/save")
    public ModelAndView saveTask(@ModelAttribute("newTask") Task task) {
        List<Task> newTask = new ArrayList<Task>();
        try {
            taskService.save(new Task(task.getDescription(), false));
            newTask.addAll(taskService.findAll());
        } catch (Exception e) {
            // Log the exception if necessary
        }
        ModelAndView mav = new ModelAndView("tasks/list-tasks");
        mav.addObject("theTask", newTask);
        return mav;
    }

    @GetMapping("/update")
    public ModelAndView update(@RequestParam("id") long id) {
        Task updatedTask = taskService.update(id);
        List<Task> tasks = taskService.findAll();
        ModelAndView mav = new ModelAndView("tasks/list-tasks");
        mav.addObject("newTask", new Task());
        mav.addObject("theTask", tasks);
        return mav;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") long theId) {
        taskService.deleteById(theId);
        List<Task> task = taskService.findAll();
        ModelAndView mav = new ModelAndView("tasks/list-tasks");
        mav.addObject("newTask", new Task());
        mav.addObject("theTask", task);
        return mav;
    }

}