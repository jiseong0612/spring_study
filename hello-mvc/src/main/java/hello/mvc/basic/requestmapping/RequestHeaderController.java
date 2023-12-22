package hello.mvc.basic.requestmapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.mvc.basic.Human;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RequestHeaderController {

	/**
	 * HTTP 헤더 정보를 조회
	 */
	@RequestMapping("/headers")
	public @ResponseBody String headers(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod,
			Locale locale, @RequestHeader MultiValueMap<String, String> headerMap, @RequestHeader("host") String host,
			@CookieValue(value = "myCookie", required = false) String cookie) {
		log.info("request={}", request);
		log.info("response={}", response);
		log.info("httpMethod={}", httpMethod);
		log.info("locale={}", locale);
		log.info("headerMap={}", headerMap);
		log.info("header host={}", host);
		log.info("myCookie={}", cookie);
		return "ok";
	}

	/**
	 * @ModelAttribute(객체) 생략 가능
	 * @RequestParam(기본형 + String 단순 타입) 생략 가능
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(HelloData helloData) {
		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
		return "ok";
	}

	/**
	 * @RequestBody - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X) -
	 *              HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
	 *			json  객체 받을 때 @RequestBody, 폼 받을때 @RequestParam(물론 생략 가능)
	 * @ResponseBody - 메시지 바디 정보 직접 반환(view 조회X) - HttpMessageConverter 사용 ->
	 *               StringHttpMessageConverter 적용
	 */
	@ResponseBody
	@PostMapping("/request-body-string-v4")
	public String requestBodyStringV4( Human messageBody) {
		log.info("messageBody={}", messageBody);
		return "ok";
	}
	
	@GetMapping(value = "/image")
	@ResponseBody
    public ResponseEntity<byte[]> getImage(HttpEntity httpEntity) throws IOException {
        // 이미지 파일 경로
        String imagePath = "/static/img/tortoy.jpg";

        // Resources 폴더에 있는 이미지 파일을 읽기 위한 Resource 객체 생성
        Resource resource = new ClassPathResource(imagePath);

        // Resource에서 파일을 읽어서 byte 배열로 변환
        byte[] imageData = Files.readAllBytes(Path.of(resource.getURI()));
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        header.setContentDisposition(ContentDisposition.attachment().filename("test.jpg").build());
        // ResponseEntity를 사용하여 이미지 데이터를 반환
        return ResponseEntity.ok().headers(header).body(imageData);
    }

	private ObjectMapper objMapper;
	@Data
	class HelloData {
		private String username;
		private int age;

		public HelloData(String username, int age) {
			this.username = username;
			this.age = age;
		}
	}
}
