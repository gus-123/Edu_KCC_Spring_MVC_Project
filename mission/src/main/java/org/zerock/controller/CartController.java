package org.zerock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.CartVO;
import org.zerock.domain.ProductVO;
import org.zerock.service.CartService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/mission/*")
@AllArgsConstructor
@Log4j
public class CartController {
	
	private CartService service;

	@GetMapping("cart")
	public void cart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.getAttribute("products");
		session.getAttribute("member");
	}
	
	@ResponseBody
	@PostMapping("cart")
	public String cartAdd(@RequestBody CartVO cartVO, HttpServletRequest request) {
		log.info("@@@@@@@@@@");
		log.info(cartVO);
		log.info("@@@@@@@@@@");
		service.cartAdd(cartVO);
		HttpSession session = request.getSession();
		session.removeAttribute("products");
		return "결제 성공!!";
		
	}
}
