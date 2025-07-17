package com.TaskMaster.TaskTrackingSystem.model;

import javax.persistence.*;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Attachment() {}

    public Attachment(Long id, String filename, String url, Task task) {
        this.id = id;
        this.filename = filename;
        this.url = url;
        this.task = task;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                '}';
    }
} 