package hello.core.repository;

import java.util.HashMap;
import java.util.Map;

import hello.core.domain.Member;

public class MemoryMemberRepository implements MemberRepository{
	public static Map<Long, Member> store = new HashMap<Long, Member>();
//	public static Long sequence = 0L;
	
	@Override
	public void save(Member member) {
//		sequence ++;
		
		store.put(member.getId(), member);
	}
	
	@Override
	public Member findById(Long id) {
		Member member = store.get(id);
		return member;
	}
	
	
	
}
