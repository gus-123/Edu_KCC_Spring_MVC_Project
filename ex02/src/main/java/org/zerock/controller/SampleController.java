package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class SampleController {
	
	// 하나만 받을때
	@GetMapping("/getSample")
	public SampleVO getSample() {
		return new SampleVO(100, "홍", "길동");
	}
	
	// 리스트 형식을 받을때
	@GetMapping("/getList")
	public List<SampleVO> getList() {
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, "first" + i, "last" + i))
				.collect(Collectors.toList());
	}
	
	// map 형식으로 받을때
	@GetMapping("/getMap")
	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		
		return map;
	}
	
	@PostMapping("/ticket")
	//@RequestBody로 객체를 json으로 받을 수 있게 해줌.
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert..............." + ticket);
		
		return ticket;
	}
	
}
