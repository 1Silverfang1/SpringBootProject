package com.silverfang.boot.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Category {
    @Id
    private String name;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "listCategory")
    private List<Post> categoryPost = new ArrayList<>();

    public List<Post> getCategoryPost() {
        return categoryPost;
    }

    public void setCategoryPost(List<Post> categoryPost) {
        this.categoryPost = categoryPost;
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
