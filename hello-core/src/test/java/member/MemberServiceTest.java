package member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.domain.Grade;
import hello.core.domain.Member;
import hello.core.service.MemberService;

public class MemberServiceTest {
	MemberService memberService;
	
	@BeforeEach
	public void beforeEach() {

//		Appconfig appConfig = new Appconfig();
//		memberService = appConfig.memberService();
	}
	@Test
	void join() {
		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);

		Member findMember = memberService.findMember(1L);

		Assertions.assertThat(member).isEqualTo(findMember);
	}
}
