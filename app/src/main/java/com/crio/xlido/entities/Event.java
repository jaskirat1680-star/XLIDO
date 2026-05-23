package com.crio.xlido.entities;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private final Long id;
    private final Long organizerId;
    private final String title;
    private List<Question> questions;

    public Event(Long organizerId, String title) {
        this.id=null;
        this.organizerId = organizerId;
        this.title = title;
    }
    public Event(Long id, Event event) {
        this.id = id;
        this.organizerId = event.organizerId;
        this.title = event.title;
        this.questions = new ArrayList<>();
    }   
    public Long getId() {
        return id;
    }
    public Long getOrganizerId() {
        return organizerId;
    }
    public String gettitle() {
        return title;
    }
    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    @Override
    public String toString() {
        return "Event [id=" + id + ", title=" + title + ", organizerId=" + organizerId
                + ", questions=" + questions + "]";
    }
    
}
