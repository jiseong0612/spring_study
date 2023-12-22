package hello.mvc.basic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LogTestController {
//	private Logger log = LoggerFactory.getLogger(getClass()); 생략 하고 @Slf4j 넣을 수 있음

	@RequestMapping("/log-test")
	public String logTest() {
		String name = "Spring";

		log.trace("trace log={}", name);
		log.debug("debug log={}", name);
		log.info(" info log={}", name);
		log.warn(" warn log={}", name);
		log.error("error log={}", name);
		
		// 로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
		log.debug("String concat log=" + name);
		return "ok";
	}
}
