package com.gogit.gogitserver.member.service;

import com.gogit.gogitserver.member.entity.Member;
import com.gogit.gogitserver.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

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
                    existingMember.setName(updatedMember.getName());
                    existingMember.setEmail(updatedMember.getEmail());
                    existingMember.setPicture(updatedMember.getPicture());

                    Member updated = memberRepository.save(existingMember);
                    return ResponseEntity.ok(updated);
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

}
