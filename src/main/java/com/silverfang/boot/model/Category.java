package com.silverfang.boot.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int categoryId;
    String name;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;
    @CreationTimestamp
    private Date updatedAt;
    @ManyToMany
    private List<Post> categoryPost = new ArrayList<>();

    public List<Post> getCategoryPost() {
        return categoryPost;
    }

    public void setCategoryPost(List<Post> categoryPost) {
        this.categoryPost = categoryPost;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
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
}
