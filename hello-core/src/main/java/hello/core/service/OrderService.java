package hello.core.service;

import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.domain.Member;
import hello.core.domain.Order;
import hello.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderService {
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;
	
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
