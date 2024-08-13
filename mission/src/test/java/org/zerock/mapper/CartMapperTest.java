package org.zerock.mapper;

import static org.junit.Assert.*;

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
public class CartMapperTest {

	@Autowired
	private CartMapper mapper;
	
	@Test
	public void test() {
		CartVO cart = CartVO.builder().id("dnjstmddjs1234").pno(1L).cnt(2L).totalPrice(2000L).build();
		log.info(mapper.cartAdd(cart));
	}

}
