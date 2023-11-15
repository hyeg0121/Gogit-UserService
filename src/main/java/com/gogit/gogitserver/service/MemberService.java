package com.gogit.gogitserver.service;

import com.gogit.gogitserver.entity.Member;
import com.gogit.gogitserver.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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
        // 1. githubId에 해당하는 회원을 데이터베이스에서 찾기
        Member existingMember = memberRepository.findByGithubId(member.getGithubId()).orElse(null);

        if (existingMember != null) {
            // 2. 이미 존재하는 경우 업데이트
            existingMember.setGithubToken(member.getGithubToken());
            // 다른 필드들도 업데이트할 수 있음

            // 업데이트된 회원을 저장
            Member updatedMember = memberRepository.save(existingMember);
            return ResponseEntity.status(HttpStatus.OK).body(updatedMember);
        } else {
            // 3. 존재하지 않는 경우 새 회원 생성
            Member savedMember = memberRepository.save(member);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
        }
    }

    public ResponseEntity<Member> updateMember(Long memberId, Member updatedMember) {
        return memberRepository.findById(memberId)
                .map(existingMember -> {
                    existingMember.setGithubId(updatedMember.getGithubId());
                    existingMember.setGithubToken(updatedMember.getGithubToken());

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

}
