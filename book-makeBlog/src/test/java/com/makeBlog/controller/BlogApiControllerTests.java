package com.makeBlog.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makeBlog.domain.Article;
import com.makeBlog.dto.AddArticleRequest;
import com.makeBlog.dto.UpdateArticleRequest;
import com.makeBlog.repository.BlogRepository;

@SpringBootTest
@AutoConfigureMockMvc // 컨트롤러 테스트시 필수
public class BlogApiControllerTests {
	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	BlogRepository blogRepository;

	@BeforeEach
	public void mockMvcSetUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		blogRepository.deleteAll();
	}

	@DisplayName("addArticle : 블로그 글 추가에 성공한다.")
	@Test
//	@Commit
	public void addArticle() throws Exception {
		final String url = "/api/articles";
		final String title = "title";
		final String content = "content123";
		final AddArticleRequest userRequest = new AddArticleRequest(title, content);

		// 객체 -> json 으로 직렬화
		final String requestsBody = objectMapper.writeValueAsString(userRequest);

		ResultActions result = mockMvc
				.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestsBody));

		result.andExpect(status().isCreated());

		List<Article> articles = blogRepository.findAll();

		assertThat(articles.size()).isEqualTo(1);
		assertThat(articles.get(0).getTitle()).isEqualTo(title);
		assertThat(articles.get(0).getContent()).isEqualTo(content);
	}

	@DisplayName("findAllArticles: 아티클 목록 조회에 성공한다.")
	@Test
	public void findAllArticles() throws Exception {
		// given
		final String url = "/api/articles";
		final String title = "title";
		final String content = "content";

		blogRepository.save(Article.builder().title(title).content(content).build());

		// when
		final ResultActions resultActions = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

		// then
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].content").value(content))
			.andExpect(jsonPath("$[0].title").value(title));
	}

	@DisplayName("findArticle: 아티클 단건 조회에 성공한다.")
	@Test
	public void findArticle() throws Exception {
		// given
		final String url = "/api/articles/{id}";
		final String title = "title";
		final String content = "content";

		Article savedArticle = blogRepository.save(Article.builder().title(title).content(content).build());

		// when
		final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.content").value(content))
				.andExpect(jsonPath("$.title").value(title));
	}

	    @DisplayName("deleteArticle: 아티클 삭제에 성공한다.")
	    @Test
	    public void deleteArticle() throws Exception {
	        // given
	        final String url = "/api/articles/{id}";
	        final String title = "title";
	        final String content = "content";

	        Article savedArticle = blogRepository.save(Article.builder()
	                .title(title)
	                .content(content)
	                .build());

	        // when
	        mockMvc.perform(delete(url, savedArticle.getId()))
	                .andExpect(status().isOk());

	        // then
	        List<Article> articles = blogRepository.findAll();

	        assertThat(articles).isEmpty();
	    }


	    @DisplayName("updateArticle: 아티클 수정에 성공한다.")
	    @Test
	    public void updateArticle() throws Exception {
	        // given
	        final String url = "/api/articles/{id}";
	        final String title = "title";
	        final String content = "content";

	        Article savedArticle = blogRepository.save(Article.builder()
	                .title(title)
	                .content(content)
	                .build());

	        final String newTitle = "new title";
	        final String newContent = "new content";

	        UpdateArticleRequest request = new UpdateArticleRequest(newTitle, newContent);

	        // when
	        ResultActions result = mockMvc.perform(put(url, savedArticle.getId())
	                .contentType(MediaType.APPLICATION_JSON_VALUE)
	                .content(objectMapper.writeValueAsString(request)));

	        // then
	        result.andExpect(status().isOk());

	        Article article = blogRepository.findById(savedArticle.getId()).get();

	        assertThat(article.getTitle()).isEqualTo(newTitle);
	        assertThat(article.getContent()).isEqualTo(newContent);
	    }
}
