package org.zerock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.MemberVO;
import org.zerock.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/mission/*")
@AllArgsConstructor
@Log4j
public class MemberController {
	
	private MemberService service;
	
	@GetMapping("user/join")
	public void join(Model model) {
		List<String> ids = service.findAllId();
		model.addAttribute("id", ids);
	}
	
	@GetMapping("user/find")
	@ResponseBody
	public List<String> userFind() {
		return service.findAllId();
	}
	
	@PostMapping("user/join")
	public String join(MemberVO member) {
		service.memberRegister(member);		
		return "redirect:/mission/user/login";
		
	}
	
	@GetMapping("user/login")
	public void login() {
		
	}
	
	
	@PostMapping("user/login")
	public String login(String id, String password, HttpServletRequest request, Model model) {
		MemberVO member = service.findByIdAndPassword(id, password);

		HttpSession session = request.getSession();
		if(member == null) {
			return "redirect:/mission/user/login";
		} else {
			session.setAttribute("member", member);
			return "redirect:/mission/main";			
		}
	}
	
	@GetMapping("user/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/mission/main";
	}
	
	
}
