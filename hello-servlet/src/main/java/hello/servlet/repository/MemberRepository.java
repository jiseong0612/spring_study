package hello.servlet.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hello.servlet.domain.member.Member;

public class MemberRepository {
	private static Map<Long, Member> store = new HashMap<Long, Member>();

	private static long sequence = 0L;

	private static final MemberRepository instance = new MemberRepository();

	private MemberRepository() {
	}

	public static MemberRepository getInstance() {
		return instance;
	}
	
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(sequence, member);
		return member;
	}
	
	public Member findById(Long id) {
		return store.get(id);
	}
	
	public List<Member>findAll(){
		return new ArrayList<Member>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
}
