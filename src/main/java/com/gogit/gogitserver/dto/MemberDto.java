package com.gogit.gogitserver.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {

    private final String githubId;
    private final String githubToken;


    @Builder
    public MemberDto(String githubId, String githubToken) {
        this.githubId = githubId;
        this.githubToken = githubToken;
    }

}
