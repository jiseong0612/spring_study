package com.makeBlog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Article {

	@Id @GeneratedValue
	@Column(updatable = false)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	@Builder
	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public void update(String title, String contet) {
		this.title = title;
		this.content = contet;
	}
}
