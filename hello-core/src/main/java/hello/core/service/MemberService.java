package hello.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.domain.Member;
import hello.core.repository.MemberRepository;

@Component
public class MemberService {
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public void join(Member member) {
		memberRepository.save(member);
	}
	
	public Member findMember(Long id) {
		return memberRepository.findById(id);
	}
}
