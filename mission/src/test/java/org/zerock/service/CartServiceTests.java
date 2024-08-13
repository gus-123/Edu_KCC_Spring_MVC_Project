package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CartVO;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CartServiceTests {

	@Autowired
	private CartService service;
	
	@Test
	public void test() {
		CartVO cart = CartVO.builder().id("dnjstmddjs1234").pno(2L).cnt(3L).totalPrice(5000L).build();
		log.info(service.cartAdd(cart));
	}

}
