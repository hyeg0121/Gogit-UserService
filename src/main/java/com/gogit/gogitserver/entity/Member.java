package com.gogit.gogitserver.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "github_id", unique = true)
    private String githubId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String picture;

    @ManyToMany
    @JoinTable(
            name = "member_liked_articles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> likedArticles;



    @Builder
    public Member(String githubId, String name, String email, String picture) {
        this.githubId = githubId;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
