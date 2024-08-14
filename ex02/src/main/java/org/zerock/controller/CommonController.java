package org.zerock.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j

public class CommonController {
	
	// '/accessError'를 처리하도록 지정
	@GetMapping("/accessError")  // 에러 메시지 대신에 accessError.jsp의 내용이 보이게 됨.
	// '/accessError'는 Authentication 타입과 파라미터를 받도록 설계해서 필요한 경우에 사용자의 정보를 확인할 수 있도록 함.
	public void accessDenied(Authentication auth, Model model) {
		log.info("access Denied: " + auth);
		model.addAttribute("msg", "Access Denied");  // 간단히 사용자가 알아볼 수 잇는 에러 메시지를 Model에 추가
	}
	
	// '/customLogin'를 처리하도록 지정
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		log.info("error: " + error);
		log.info("logout: " + logout);
		
		if(error != null) {
			model.addAttribute("error", "Login error!!!!");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "Logout!!!!");
		}
		
	}
	
	@GetMapping("/customLogout")
	public void logoutGET() {
		log.info("logout GET");
	}
	
	// post일때만 로그아웃(즉, 로그아웃 버튼을 눌러야만 가능 함)
	@PostMapping("/customLogout")
	public void logoutPOST() {
		log.info("logout POST");
	}
}
