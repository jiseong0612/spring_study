package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

public class MemberService {
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Long join(Member member) {
		validateDuplicateMember(member); // �ߺ� ȸ�� ����
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName()).ifPresent(m -> {
			throw new IllegalStateException("중복된 회원");
		});
	}

	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
