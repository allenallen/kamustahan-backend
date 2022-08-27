package com.tamaraw.kamustahan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WebUser {

    @Id
    private String id;

    private String username;

    private String email;

    public WebUser(String id, String username, String email) {
        this.username = username;
        this.email = email;
        this.id = id;
    }

    protected WebUser() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
