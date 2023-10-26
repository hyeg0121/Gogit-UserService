package com.gogit.gogitserver.dto;

import com.gogit.gogitserver.entity.Member;
import lombok.Builder;

public class ArticleDto {
    private Member writer;
    private String contents;
    private Long likes;

    @Builder
    public ArticleDto(Member writer, String contents, Long likes){
        this.writer = writer;
        this.contents = contents;
        this.likes = likes;
    }

}
