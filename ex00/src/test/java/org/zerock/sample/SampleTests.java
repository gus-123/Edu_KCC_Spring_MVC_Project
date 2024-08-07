package org.zerock.sample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
// Spring Container의 context를 가져오기 위한 용도(즉,  테스트를 실행하기 위한 환경을 설정)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTests {
	
	@Autowired
	private Restaurant restaurant;
	
	@Test 
	public void test() {
		assertNotNull(restaurant);
		log.info(restaurant);
		log.info(restaurant.getChef());
	}

}
