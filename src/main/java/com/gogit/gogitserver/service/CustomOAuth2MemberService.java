package com.gogit.gogitserver.service;

import com.gogit.gogitserver.entity.Member;
import com.gogit.gogitserver.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2MemberService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    private final HttpSession session;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        saveOrUpdates(user);
        return user;
    }

    private Member saveOrUpdates(OAuth2User oAuth2User) {

        Member oAuthMember = Member.builder()
                .githubId(oAuth2User.getAttribute("login"))
                .name(oAuth2User.getAttribute("name"))
                .picture(oAuth2User.getAttribute("avartar_url"))
                .build();

        return memberRepository.save(oAuthMember);
    }


}
