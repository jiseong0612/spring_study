package com.makeBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeBlog.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long>{

}
