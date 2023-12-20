package com.makeBlog.dto;

import com.makeBlog.domain.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
/*
 * DTO는 계층끼리 데이터를 교환하기 위해 사용하는 객체, (비즈니스 로직x)
 * DAO는 데이터베이스와 연결되고 데이터를 조회하고 수정하는데 사용하는 객체
 */
public class AddArticleRequest {	//DTO

	private String title;
	private String content;
	
	//DTO를 엔티티로 만들어주는 메서드
	public Article toEntity() {
		return Article.builder()
			.title(title)
			.content(content).build();
	}
}
