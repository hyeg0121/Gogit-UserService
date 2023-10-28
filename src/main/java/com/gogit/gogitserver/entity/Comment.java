package com.gogit.gogitserver.entity;

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
    @JoinColumn(name = "post_id")
    private Post post;
}
