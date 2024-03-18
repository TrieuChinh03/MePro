package com.example.mepro.layout_note.model;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    private Integer id;
    private String tilte;
    private String  content;
    private String time;
    
    public Note() {}
    
    public Note(String tilte, String content, String time) {
        this.tilte = tilte;
        this.content = content;
        this.time = time;
    }
    
    public Note(Integer id, String tilte, String content, String time) {
        this.id = id;
        this.tilte = tilte;
        this.content = content;
        this.time = time;
    }
    
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTilte() {
        return tilte;
    }
    
    public void setTilte(String tilte) {
        this.tilte = tilte;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
}
