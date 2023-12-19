package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping("hello-mvc")
	 public String helloMvc(@RequestParam("name") String name, Model model) {
	 model.addAttribute("name", name);
	 return "hello-template";
	 }
	
	@GetMapping("hello-api")
	@ResponseBody	//뷰가 아닌 http의  Body 에 문자내용을 직접 반환(HttpMessageConverter)
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}
	
	static class Hello{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
