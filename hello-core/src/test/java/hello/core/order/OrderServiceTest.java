package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.Appconfig;
import hello.core.domain.Grade;
import hello.core.domain.Member;
import hello.core.domain.Order;
import hello.core.service.MemberService;
import hello.core.service.OrderService;

public class OrderServiceTest {
	private MemberService memberServcie;
	private OrderService orderService;
	
	@BeforeEach
	public void beforeEach() {
		Appconfig appConfig = new Appconfig();
		memberServcie = appConfig.memberService();
		orderService = appConfig.orderService();
	}

	@Test
	public void createOrder() {
		Member member = new Member(1L, "AAA", Grade.VIP);
		
		memberServcie.join(member);
		
		Member findMember = memberServcie.findMember(1L);
		
		Order order = orderService.createOrder(findMember.getId(), "itemA", 800);
		
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(80);
	}
}
