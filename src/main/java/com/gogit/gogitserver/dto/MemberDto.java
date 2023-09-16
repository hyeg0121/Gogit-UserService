package com.gogit.gogitserver.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {

    private final String githubID;
    private final String name;
    private final String email;


    @Builder
    public MemberDto(String githubID, String name, String email) {
        this.githubID = githubID;
        this.name = name;
        this.email = email;
    }

}
