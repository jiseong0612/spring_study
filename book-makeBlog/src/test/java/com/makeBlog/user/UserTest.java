package com.makeBlog.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.makeBlog.domain.User;
import com.makeBlog.repository.UserRepository;
@SpringBootTest
public class UserTest {

	@Autowired
	private UserRepository repository;
	
	@Test
	public void findByEmail() {
		String email = "hth5545@gmail.com";
		User findUser = repository.findByEmail(email).get();
		
		System.out.println("findUser >>> " + findUser.toString());
		
		
	}
}
