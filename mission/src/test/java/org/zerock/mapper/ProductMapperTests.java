package org.zerock.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ProductVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ProductMapperTests {

	@Autowired
	private ProductMapper mapper;
	
//	@Test
//	public void test() {
//		ProductVO product = ProductVO.builder().pname("해물라면").price(6000L).pimage("/resources/images/해물라면.png").build();
//		log.info(mapper.productAdd(product));
//	}
	
	@Test
	public void getListTest() {
		log.info(mapper.getList());
	}

}
