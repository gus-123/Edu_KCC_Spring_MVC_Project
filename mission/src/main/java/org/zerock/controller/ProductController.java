package org.zerock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.ProductVO;
import org.zerock.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@Controller
@RequestMapping("/mission/*")
@Log4j
public class ProductController {
	private ProductService serice;
	
	@GetMapping("main")
	public String main(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session == null) {
			model.addAttribute("member", "null");
		}
		session.getAttribute("member");
		
		List<ProductVO> list = serice.getList();
		model.addAttribute("list", list);
		return "/mission/main";
	}
	
	@ResponseBody
	@PostMapping("main")
	public String addToCart(@RequestBody List<ProductVO> products, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		List<ProductVO> cart = (List<ProductVO>)session.getAttribute("products");
		if(cart == null) {
			cart = new ArrayList<ProductVO>();
		}
		
		cart.addAll(products);
		session.setAttribute("products", cart);
		
		return "success to add to cart!!";
	}
	
}
