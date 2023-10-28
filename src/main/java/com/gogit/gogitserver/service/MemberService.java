package com.gogit.gogitserver.service;

import com.gogit.gogitserver.entity.Member;
import com.gogit.gogitserver.entity.Post;
import com.gogit.gogitserver.repository.MemberRepository;
import com.gogit.gogitserver.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public ResponseEntity<Member> getMemberByID(Long memberId) {
        return memberRepository.findById(memberId)
                .map(member -> ResponseEntity.ok(member))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Member> createMember(Member member) {
        Member savedMember = memberRepository.save(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    public ResponseEntity<Member> updateMember(Long memberId, Member updatedMember) {
        return memberRepository.findById(memberId)
                .map(existingMember -> {
                    existingMember.setGithubId(updatedMember.getGithubId());
                    existingMember.setGithubToken(updatedMember.getGithubToken());
                    existingMember.setLikedPosts(updatedMember.getLikedPosts());

                    Member updated = memberRepository.save(existingMember);
                    return ResponseEntity.status(200).body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteMember(Long memberId) {
        return memberRepository.findById(memberId)
                .map(existingMember -> {
                    memberRepository.delete(existingMember);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public Member findByGithubId(String githubId) {
        return memberRepository.findByGithubId(githubId).orElse(null);
    }

    public List<Post> findPostByWriterId(Long writerId) {
        return postRepository.findByWriterId(writerId);
    }


}
