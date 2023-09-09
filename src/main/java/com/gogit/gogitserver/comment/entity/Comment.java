package com.gogit.gogitserver.comment.entity;

import com.gogit.gogitserver.article.entity.Article;
import com.gogit.gogitserver.member.entity.Member;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member author;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
