package com.crio.xlido.entities;

public class Reply {
    private Long userId;
    private String Content;
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getContent() {
        return Content;
    }
    public void setContent(String content) {
        Content = content;
    }
    public Reply(Long userId, String content) {
        this.userId = userId;
        Content = content;
    }
    
}
