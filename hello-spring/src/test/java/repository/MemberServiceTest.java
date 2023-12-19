package repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;

class MemberServiceTest {
	MemberService memberService;
	MemoryMemberRepository memberRepository;

	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	public void 회원가입() throws Exception {
		// Given
		Member member = new Member();
		member.setName("hello");
		// When
		Long saveId = memberService.join(member);
		// Then
		Member findMember = memberRepository.findById(saveId).get();
		assertEquals(member.getName(), findMember.getName());
	}

	@Test
	public void 중복_회원_예외() throws Exception {
		// Given
		Member member1 = new Member();
		member1.setName("spring");
		Member member2 = new Member();
		member2.setName("spring");
		// When
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));	//예외가 발생해야 한다.
		assertThat(e.getMessage()).isEqualTo("�̹� �����ϴ� ȸ���Դϴ�.");
	}
}