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
    public String secured() throws IOException {

        return "ok";
    }

}
