package com.makeBlog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.makeBlog.domain.Article;
import com.makeBlog.dto.AddArticleRequest;
import com.makeBlog.dto.ArticleResponse;
import com.makeBlog.dto.UpdateArticleRequest;
import com.makeBlog.service.BlogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
	private final BlogService blogService;

	@PostMapping("/api/articles")
	public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
		Article saveArticle = blogService.save(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(saveArticle);
	}

	/**
	 * 글 전체를 조회한 다음, 응답용 객체로 파싱해 body에 담아 리턴
	 */
	@GetMapping("/api/articles")
	public ResponseEntity<List<ArticleResponse>> findAllArticles() {
		List<ArticleResponse> articles = blogService.findAll().stream().map(ArticleResponse::new)
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(articles);
	}

	@GetMapping("/api/articles/{id}")
	public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
		Article article = blogService.findById(id);

		return ResponseEntity.ok().body(new ArticleResponse(article));
	}
	
	@DeleteMapping("/api/articles/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable long id){
		blogService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/api/articles/{id}")
	public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request){
		Article updateArticle = blogService.update(id, request);
		
		return ResponseEntity.ok().body(updateArticle);
		
	}
}
