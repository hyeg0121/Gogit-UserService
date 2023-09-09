package com.gogit.gogitserver.member.entity;

import com.gogit.gogitserver.article.entity.Article;
import jakarta.persistence.*;
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

    @Column(name = "github_token")
    private String githubToken;

    @ManyToMany
    @JoinTable(
            name = "member_liked_articles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> likedArticles;
}
