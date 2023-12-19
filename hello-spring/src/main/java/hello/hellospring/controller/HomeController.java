package hello.hellospring.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberForm;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
}
