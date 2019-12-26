package com.silverfang.boot.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

public class UserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int userId;
    @OneToMany(mappedBy = "userTable")
    private List<Post> postList= new ArrayList<>();

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    String name;
    public int getUserId() {
        return userId;
    }

    public UserTable() {
    }

    public UserTable(String name, String email, String password ) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    String email;
    String password;
    @CreationTimestamp
    private Date createdAt;
    @CreationTimestamp
    private Date updatedAt;
}
