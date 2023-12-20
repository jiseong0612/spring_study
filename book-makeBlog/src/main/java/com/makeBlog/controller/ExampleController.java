package com.makeBlog.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ExampleController {

    @GetMapping("/thymeleaf/example22")
    public String thymeleafExample(Model model) {
    	log.info("example..................................");
        Person examplePerson = new Person();
        examplePerson.setId(11L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));

        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());

        return "example";
    }

   @Data
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}

