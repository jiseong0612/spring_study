package hello.mvc.basic;

import lombok.Data;

@Data
public class Human {
	private String name;
	private int age;
	
	public Human() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + "]";
	}
}
