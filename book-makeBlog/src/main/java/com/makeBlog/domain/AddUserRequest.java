package com.makeBlog.domain;

import lombok.Data;

@Data
public class AddUserRequest {
	private String email, password;
}
