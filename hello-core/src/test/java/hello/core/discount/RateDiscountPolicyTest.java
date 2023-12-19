package hello.core.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.domain.Grade;
import hello.core.domain.Member;

public class RateDiscountPolicyTest {
	private DiscountPolicy discountPolicy = new RateDiscountPolicy();
	
	@Test
	@DisplayName("VIP 는 10% 할인이 적용 되어야 한다.")
	public void vip_o() {
		Member member = new Member(1L, "aaa", Grade.VIP);
		int discountPrice = discountPolicy.discount(member, 5000);
		
		Assertions.assertThat(discountPrice).isEqualTo(500);
	}
	
	@Test
	@DisplayName("VIP 가 아니면 10% 할인이 적용 되지 말야 한다.")
	public void vip_x() {
		Member member = new Member(1L, "aaa", Grade.BASIC);
		int discountPrice = discountPolicy.discount(member, 5000);
		
		Assertions.assertThat(discountPrice).isEqualTo(0);
	}
}
