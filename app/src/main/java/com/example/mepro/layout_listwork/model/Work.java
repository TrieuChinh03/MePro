package com.example.mepro.layout_listwork.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Work implements Serializable {
    private int id;
    private String titleWork;
    private List<String> extraWork;
    private String noteWork;
    private String startTime;
    private String endTime;
    private boolean important;
    private boolean completed;
    private int category;

    public Work() {}

    public Work(String titleWork, int category, boolean complete) {
        this.titleWork = titleWork;
        this.category = category;
        this.completed = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleWork() {
        return titleWork;
    }

    public void setTitleWork(String titleWork) {
        this.titleWork = titleWork;
    }

    public List<String> getExtraWork() {
        return extraWork;
    }

    public void setExtraWork(List<String> extraWork) {
        this.extraWork = extraWork;
    }

    public String getNoteWork() {
        return noteWork;
    }

    public void setNoteWork(String noteWork) {
        this.noteWork = noteWork;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
