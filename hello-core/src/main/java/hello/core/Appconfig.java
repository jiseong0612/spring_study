package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.OrderService;

@Configuration
public class Appconfig {

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public OrderService orderService() {
		return new OrderService(memberRepository(), discountPolicy());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	@Bean
	public DiscountPolicy discountPolicy() {
		return new FixDiscountPolicy();
	}
}
