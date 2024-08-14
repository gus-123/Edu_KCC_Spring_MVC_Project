package org.zerock.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
// 로그인 성공 후에 하고 싶은 작업을 등록 시켜주는 것 임.
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	//Authentication auth - 인증 정보를 받음.
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		log.warn("Login Success");

		// 로그인한 사용자의 권한을 리스트에 저장
		List<String> roleNames = new ArrayList<>();

		auth.getAuthorities().forEach(authority -> {

			roleNames.add(authority.getAuthority());

		});

		log.warn("ROLE NAMES: " + roleNames);
		
		// 로그인 한 사용자에 부여된 권한을 Authentication 객체를 이용해서 권한에 따라 다른 페이지를 호출 시켜 줌.
		if (roleNames.contains("ROLE_ADMIN")) {

			response.sendRedirect("/sample2/admin");
			return;
		}

		if (roleNames.contains("ROLE_MEMBER")) {

			response.sendRedirect("/sample2/member");
			return;
		}

		response.sendRedirect("/");
	}
}


