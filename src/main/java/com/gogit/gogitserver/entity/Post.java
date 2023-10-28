package com.gogit.gogitserver.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    @Column(length = 2000)
    private String contents;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany(mappedBy = "likedPosts")
    private List<Member> likedMember;

    @Column(name = "created_at")
    private Date createdAt;

    @Builder
    public Post(Member writer, String contents) {
        this.writer = writer;
        this.contents = contents;
        this.createdAt = new Date();
    }


}
