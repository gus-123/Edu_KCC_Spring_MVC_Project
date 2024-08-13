package org.zerock.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.MemberVO;

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {

	@Autowired
	private MemberMapper mapper;
	
//	@Test
//	public void test() {
//		MemberVO member = 
//		MemberVO.builder().id("dnjstmddjs12").password("1234").name("원승언").email("dnjstmddjs12@naver.com").birth("1997-03-06").build();
//		System.out.println(member);
//		log.info("@@@@@@@@@@@@@@@");
//		log.info(mapper.memberRegister(member));
//		log.info("@@@@@@@@@@@@@@@");
//		
//	}
	
//	@Test
//	public void findMemberByIdTest() {
//		log.info("@@@@@@@@@@@@@@@@@@@@@@@");
//		log.info(mapper.findMemberById("dnjstmddjs12"));
//		log.info("@@@@@@@@@@@@@@@@@@@@@@@");
//	}
	
//		@Test
//		public void findAllIdTest() {
//			mapper.findAllId().forEach(id -> log.info(id));
//		}
	
		@Test
		public void findByIdAndPasswordTest() {
			log.info(mapper.findByIdAndPassword("dnjstmddjs1234", "1234"));
		}
}
