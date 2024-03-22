package com.example.mepro.layout_listword.model;

import java.time.LocalDateTime;
import java.util.List;

public class Work {
    private int id;
    private String titleWork;
    private List<String> extraWork;
    private String noteWork;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean important;
    private boolean complete;
    private int category;

    public Work() {}

    public Work(String titleWork, int category, boolean complete) {
        this.titleWork = titleWork;
        this.category = category;
        this.complete = complete;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
