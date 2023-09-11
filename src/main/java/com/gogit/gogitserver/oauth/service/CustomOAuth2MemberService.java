package com.gogit.gogitserver.oauth;

import com.gogit.gogitserver.member.entity.Member;
import com.gogit.gogitserver.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2MemberService implements OAuth2UserService {

    private final MemberRepository memberRepository;
    private final HttpSession session;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        DefaultOAuth2UserService service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);

        System.out.println(oAuth2User);
        Member member = saveOrUpdates(oAuth2User);
        return null;
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
