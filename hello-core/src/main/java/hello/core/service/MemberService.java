package hello.core.service;

import org.springframework.stereotype.Component;

import hello.core.domain.Member;
import hello.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	
	public void join(Member member) {
		memberRepository.save(member);
	}
	
	public Member findMember(Long id) {
		return memberRepository.findById(id);
	}
}
