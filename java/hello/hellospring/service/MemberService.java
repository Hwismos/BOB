package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.*;

public class MemberService
{
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원가입
    public Long join(Member member)
    {
        validateDuplicateMember(member);    // 중복회원 확인
        memberRepository.save(member);      // 중복회원 미존재시 repository에 저장
        return (member.getId());
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->
                {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체조회
    public List<Member> findMembers()
    {
        return (memberRepository.findAll());
    }

    public Optional<Member> findOne(Long memberId)
    {
        return (memberRepository.findById(memberId));
    }
}