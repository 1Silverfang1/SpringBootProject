package com.silverfang.bootblog;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter

@NoArgsConstructor
@ToString
@Entity
public class Post {
    @Id
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Post(int id, String title, String content, String published, String authorId, String updatedAt, String createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.published = published;
        this.authorId = authorId;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    String title;
    String content;
    String published;
    String authorId;
    String updatedAt;
    String createdAt;
}
