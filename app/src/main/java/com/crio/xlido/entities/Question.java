package com.crio.xlido.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Question {
    private final Long id;
    private final Long eventId;
    private final Long userId;
    private Set<Long> votes;
    private String content;
    private Map<Long, String> reply;
    public Question(Long eventId, Long userId, String content) {
        this.id = null;
        this.eventId = eventId;
        this.userId = userId;
        this.content = content;
    }
    public Question(Long id, Question question) {
        this.id = id;
        this.eventId = question.eventId;
        this.userId = question.userId;
        this.content = question.content;
        reply = new HashMap<>();
        votes = new HashSet<>();
    }
    public Long getId() {
        return id;
    }
    public Long getEventId() {
        return eventId;
    }
    public Long getUserId() {
        return userId;
    }
    public Set<Long> getVotes() {
        return votes;
    }
    public void setVotes(Set<Long> votes) {
        this.votes = votes;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Map<Long, String> getReply() {
        return reply;
    }
    public void setReply(Map<Long, String> reply) {
        this.reply = reply;
    }
    @Override
    public String toString() {
        return "Question [content=" + content + ", eventId=" + eventId + ", id=" + id + ", reply="
                + reply + ", userId=" + userId + ", votes=" + votes + "]";
    }
    
}
