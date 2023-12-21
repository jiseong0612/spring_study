package hello.servlet.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.servlet.domain.member.Member;
import hello.servlet.repository.MemberRepository;

public class MemberRepositoryTests {
	MemberRepository memberRepository = MemberRepository.getInstance();
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member("hello", 20);
		
		Member saveMember = memberRepository.save(member);
		
		Member findMember = memberRepository.findById(member.getId());
		
		Assertions.assertThat(findMember).isEqualTo(saveMember);
		
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member("hello1", 20);
		Member member2 = new Member("hello2", 30);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		List<Member> members = memberRepository.findAll();
		
		Assertions.assertThat(members.size()).isEqualTo(2);
		Assertions.assertThat(members).contains(member1, member2);
		
	}
}
