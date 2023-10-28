package com.gogit.gogitserver.dto;

import com.gogit.gogitserver.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddPostRequest {
    private final Member writer;
    private final String contents;

    @Builder
    public AddPostRequest(Member writer, String contents){
        this.writer = writer;
        this.contents = contents;
    }

}
