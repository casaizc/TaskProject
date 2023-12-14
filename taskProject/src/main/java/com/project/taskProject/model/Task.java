package com.project.taskProject.model;

import javax.persistence.*;

@Entity
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private boolean status;

    public Task() {

    }

    public Task(String description, boolean status) {
        this.description = description;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tasks [id=" + id + ", desc=" + description + ", status=" + status + "]";
    }

}