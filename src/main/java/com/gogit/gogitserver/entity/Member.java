package com.gogit.gogitserver.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "github_id", unique = true)
    private String githubId;

    @Column(name = "github_token", unique = true)
    private String githubToken;

    @ManyToMany
    @JoinTable(
            name = "member_liked_posts",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> likedPosts;

    @Builder
    public Member(String githubId, String githubToken) {
        this.githubId = githubId;
        this.githubToken = githubToken;
    }


}
