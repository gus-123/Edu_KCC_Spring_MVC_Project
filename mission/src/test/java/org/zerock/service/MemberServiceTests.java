package org.zerock.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapperTests;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTests {

	@Autowired
	private MemberService service;
	
//	@Test
//	public void test() {
//		MemberVO member = MemberVO.builder().id("dnjstmddjs123").password("1234").name("WON").email("dnjstmddjs123@naver.com")
//				.birth(LocalDate.of(2022, 02, 02)).build();
//		log.info(service.memberRegister(member));
//	}
	
	@Test
	public void findByIdAndPasswordTest() {
		log.info(service.findByIdAndPassword("dnjstmddjs1234", "1234"));
	}

}
