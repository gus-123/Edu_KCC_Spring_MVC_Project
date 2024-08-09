package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

//JUnit 테스트를 실행할 때 스프링 컨테이너를 사용하여 테스트를 진행할 수 있음
@RunWith(SpringJUnit4ClassRunner.class)
//해당 테스트에서 사용될 애플리케이션 컨텍스트가 WebApplicationContext가 되도록 설정
// Servlet의 ServletContext를 이용하기 위해서인데, 스프링에서는 WebApplicationContext라는 존재를 이용하기 위해서 이다.
@WebAppConfiguration
//Spring Container의 context를 가져오기 위한 용도(즉,  테스트를 실행하기 위한 환경을 설정)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {
	
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;  // 가상의 톰캣의 역할을 해줌. (MockMvc 객체 생성 - 즉, 가짜 mvc)
	
	// 가상의 톰캣을 만들어줬다.
	// 테스트 메서드가 실행되기 전에 한 번만 실행되는 @Before 어노테이션을 사용하여 mockMvc 객체를 초기화하는 역할을 함.
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void test() throws Exception {
		// MockMvc 객체를 이용한 가상 웹 환경 구축(즉, 가상의 톰캣)하여 GET 요청 시뮬레이션 방식으로 "/board/list"의 응답 결과 확인 값을 보여줌.
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				// 요청에 대한 응답 결과를 얻어 Model과 View 정보를 로그에 출력
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
}
