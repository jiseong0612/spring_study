package hello.core.discount;

import org.springframework.stereotype.Component;

import hello.core.domain.Grade;
import hello.core.domain.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

	private int discountPercent = 10;

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			return price = price * discountPercent / 100;
		} else {
			return 0;
		}
	}

}
