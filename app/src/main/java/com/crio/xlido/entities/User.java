package com.crio.xlido.entities;

public class User {
    private final Long id;
    private String email;
    private String password;
    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public User(String email, String password) {
        this.id =  null;
        this.email = email;
        this.password = password;
    }
    public User(Long id, User entity) {
        this.id = id;
        this.email = entity.email;
        this.password = entity.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User [id=" + id + "]";
    }

    

    
}
