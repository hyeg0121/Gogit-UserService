package com.gogit.gogitserver.entity;

import com.gogit.gogitserver.entity.Comment;
import com.gogit.gogitserver.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    @Column(length = 2000)
    private String contents;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany(mappedBy = "likedArticles")
    private List<Member> likedMember;

    private Long likes;

    @Builder
    public Article(Member writer, String contents) {
        this.writer = writer;
        this.contents = contents;
    }


}
