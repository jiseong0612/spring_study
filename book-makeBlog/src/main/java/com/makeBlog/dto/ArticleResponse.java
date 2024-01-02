package com.makeBlog.dto;

import java.time.LocalDateTime;

import com.makeBlog.domain.Article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ArticleResponse {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	
	public ArticleResponse(Article article) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.createdAt = article.getCreatedAt();
	}
}
