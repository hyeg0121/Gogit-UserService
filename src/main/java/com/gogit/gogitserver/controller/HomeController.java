package com.gogit.gogitserver.controller;

import com.gogit.gogitserver.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello, Home";
    }

    @GetMapping("/secured")
    public ResponseEntity secured(@AuthenticationPrincipal OAuth2User oAuth2User,
                          HttpSession session) throws IOException {

        GitHub gitHub = new GitHubBuilder()
                .withOAuthToken(session.getAttribute("oAuthToken").toString(),
                        oAuth2User.getName()).build();

        GHUser user = gitHub.getUser(oAuth2User.getName());
        System.out.println("user = " + user);

        MemberDto dto = MemberDto.builder()
                .githubID(user.getLogin())
                .name(user.getName())
                .email(user.getEmail())
                .build();


        return ResponseEntity.ok(dto);
    }

}
