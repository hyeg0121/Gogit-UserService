package com.gogit.gogitserver.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {

    private final String githubID;
    private final String githubToken;


    @Builder
    public MemberDto(String githubID, String githubToken) {
        this.githubID = githubID;
        this.githubToken = githubToken;
    }

}
