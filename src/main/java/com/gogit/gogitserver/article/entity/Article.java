package com.gogit.gogitserver.article.entity;

import com.gogit.gogitserver.comment.entity.Comment;
import com.gogit.gogitserver.member.entity.Member;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    @Column(name = "content", length = 2000)
    private String contents;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany(mappedBy = "likedArticles")
    private List<Member> likes;
}
