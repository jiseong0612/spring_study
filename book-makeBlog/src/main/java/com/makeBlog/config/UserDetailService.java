package com.makeBlog.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.makeBlog.domain.User;
import com.makeBlog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService{

	private final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findByEmail(email).get();
		
		System.out.println("user >>> "  + user.toString());
		
		return repository.findByEmail(email).orElseThrow(()->new IllegalArgumentException(email));
	}
	
	
}
