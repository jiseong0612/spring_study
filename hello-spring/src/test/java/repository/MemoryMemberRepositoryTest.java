package repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();

	//콜백 메서드, 각 테스트가 끝난 후 호출
	@AfterEach	
	public void afterEach() {
		repository.clearStore();
	}

	@Test
	public void save() {
		// given
		Member member = new Member();
		member.setName("spring");
		// when
		repository.save(member);
		// then
		Member result = repository.findById(member.getId()).get();

		assertThat(result).isEqualTo(member);
	}

	@Test
	public void findByName() {
		// given
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		// when
		Member result = repository.findByName("spring1").get();
		// then
		assertThat(result).isEqualTo(member1);
	}

	@Test
	public void findAll() {
		// given
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		// when
		List<Member> result = repository.findAll();
		// then
		assertThat(result.size()).isEqualTo(2);
	}
}