package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.OrderService;

public class Appconfig {
	
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	public OrderService orderService() {
		return new OrderService(memberRepository(), discountPolicy()); 
	}
	
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
	public DiscountPolicy discountPolicy() {
		return new FixDiscountPolicy();
	}
}
