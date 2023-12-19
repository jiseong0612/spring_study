package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.domain.Grade;
import hello.core.domain.Member;
import hello.core.domain.Order;
import hello.core.service.MemberService;
import hello.core.service.OrderService;

public class OrderApp {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
		
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		OrderService orderService  = applicationContext.getBean("orderService", OrderService.class);
		
		Member member= new Member(1L, "aaa", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(member.getId(), "itemA", 772);
		int discountPrice = order.getDiscountPrice();
		
		System.out.println("discountPrice : " + discountPrice);
		
	}
}
