package org.zerock.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;
import oracle.net.aso.l;

@RunWith(SpringJUnit4ClassRunner.class)
//Spring Container의 context를 가져오기 위한 용도(즉,  테스트를 실행하기 위한 환경을 설정)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Autowired
	private BoardService service;

//   register TEST
//	@Test
//	public void test() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로운 글 4");
//		board.setContent("새로운 글내용4");
//		board.setWriter("user01");
//		
//		service.register(board);
//		log.info("글번호: " + board.getBno());
//	}

//   select TEST
//	@Test
//	public void testGetList() {
//		service.getList().forEach(board-> log.info(board));
//	}

//   select TEST(bno 지정)	
//	@Test
//	public void testGet() {
//		log.info(service.get(4L));
//	}

// update TEST
//	@Test
//	public void testUpdate()  {
//		BoardVO board = service.get(4L);
//		
//		if(board == null) {
//			return;
//		}
//		
//		board.setTitle("제목을 수정합니다.");
//		log.info("modify result: " + service.modify(board) );
//	}
	
	//delete TEST
	@Test
	public void testdelete() {
		log.info("result delete: " + service.remove(4L));
	}
	
}
