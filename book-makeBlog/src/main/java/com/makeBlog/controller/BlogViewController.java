package com.makeBlog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.makeBlog.domain.Article;
import com.makeBlog.dto.ArticleResponse;
import com.makeBlog.service.BlogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
	private final BlogService blogService;
	
	@GetMapping("/articles")
	public String getArticles(Model model) {
		List<ArticleResponse> articles = blogService
				.findAll()
				.stream()
				.map(ArticleResponse::new).collect(Collectors.toList());
		
		model.addAttribute("articles", articles);
		return "articleList";
	}
	
	@GetMapping("/articles/{id}")
	public String getArticle(@PathVariable Long id, Model model	) {
		Article article = blogService.findById(id);
		
		model.addAttribute("article", new ArticleResponse(article));
		
		return "article";
	}
	
	@GetMapping("/new-article")
	public String newArticle(Long id, Model model) {
		if(id == null) {
			model.addAttribute("article", new ArticleResponse());
		}else {
			Article article = blogService.findById(id);
			model.addAttribute("article", new ArticleResponse(article));
		}
		return "newArticle";
	}
}
