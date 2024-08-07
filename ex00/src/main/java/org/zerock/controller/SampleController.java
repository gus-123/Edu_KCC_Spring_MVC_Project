package org.zerock.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;
import oracle.net.aso.l;

@Controller
//경로를 공통으로 뽑아내기 위해서 @RequestMapping를 사용 함.
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	@GetMapping("basic")
	public void basicGet() {
		log.info("basic........");
	}
	
	@GetMapping("ex01")
	// SampleDTO dto 객체로 데이터를 받음
	public String ex01(SampleDTO dto) {
		log.info("ex01........");
		log.info("dto: " + dto);
		return "ex01";
	}
	
	@GetMapping("ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("이름: " + name);
		log.info("나이: " + age);
		return "ex02"; 
	}
	
	@GetMapping("ex03")
	// RedirectAttributes 데이터값을 리다이렉션한 페이지에 전달 하게 해줌.
	public String ex03(RedirectAttributes rttr) {
		rttr.addAttribute("name", "bbb");
		rttr.addAttribute("age", 30);
		rttr.addAttribute("page", "100");
		
		return "redirect:/sample/ex04";
	}
	
	//View => Controller => View 데이터 전달
	// 객체는 @ModelAttribute 해도그만 안해도 그만
	// 일반 변수(그대로 또다른 view에 전달하여 출력하고 싶을때)는 무조건 @ModelAttribute을 사용해야 함.
	//  -> 이름이 같으면 @RequestParam 생략 가능
 	@GetMapping("ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("ex04........");
		log.info("dto: " + dto);
		log.info("page: " + page);
		return "ex04";
	}
	
	@GetMapping("ex05")
	//void일때는 url 경로에 맞는 폴더에 .jsp 파일을 넣어야 한다.
	public void ex05() {
		log.info("ex05......");
	}
	
	@GetMapping("ex06")
	// 1. 객체를 json으로 받음
	//Java 객체를 JSON 문자열로 변환하거나, JSON 문자열을 Java 객체로 변환하는 작업을 수행(@ResponseBody)
	public @ResponseBody SampleDTO ex06() {
		SampleDTO dto = new SampleDTO();
		dto.setName("홍길동");
		dto.setAge(20);
		
		return dto; //json
	}
	
	@GetMapping("ex07")
	//2. 객체를 json으로 받으면서 header, HttpStatus.OK도 정보를 추가해서 받기 위함.
	//json 값은 String임
	public ResponseEntity<String> ex07() {
		log.info("ex07..........");
		
		//{"name":"홍길동"}, (리스트, 맵을 넣어도 됨.)
		String message = "{\"name\":\"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
//		안녕하세요 이수호 입니다. 다녀갑니다. 다시오겠습니다. 반갑습니다. 현민형님 안녕하세용. 키보드가 야무진거 같습니다.
		return new ResponseEntity<String>(message, header, HttpStatus.OK);
	}
}