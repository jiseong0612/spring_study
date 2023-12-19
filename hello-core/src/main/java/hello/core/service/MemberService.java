package hello.core.service;

import org.springframework.stereotype.Service;

import hello.core.domain.Member;
import hello.core.repository.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
//	@Autowired
//	public MemberService(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}
	
	public void join(Member member) {
		memberRepository.save(member);
	}
	
	public Member findMember(Long id) {
		return memberRepository.findById(id);
	}
}
