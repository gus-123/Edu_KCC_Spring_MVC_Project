package org.zerock.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//Spring Container의 context를 가져오기 위한 용도(즉,  테스트를 실행하기 위한 환경을 설정)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
//Spring을 사용하여 BoardMapper 인스턴스를 주입하고 getList() 메서드를 사용하여 보드 목록을 검색하고 각 보드의 정보를 기록
	
	@Autowired
	private BoardMapper mapper;
	
//    getList TEST	
//	@Test
//	public void test() {
//		mapper.getList().forEach(board -> log.info(board));
//	}
	
	//Insert TEST
//	@Test
//	public void testInsert() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로운 글2");
//		board.setContent("새로작성한글2");
//		board.setWriter("newUser2");
//		
//		mapper.insert(board);
//	}
	
	//insertSelectKey TEST
//	@Test
//	public void testInsertSelectKey() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로운 글3");
//		board.setContent("새로작성한글3");
//		board.setWriter("newUser3");
//		
//		mapper.insertSelectKey(board);
//		log.info(board);
//	}
	
	//read TEST
//	@Test
//	public void testRead() {
//		BoardVO board = mapper.read(3L);
//		log.info(board);
//	}
	
	//delete TEST
//	@Test
//	public void testDelete() {
//		log.info("Delete count: " + mapper.delete(3L));
//	}
	
	//update TEST
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(2L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");
		
		int count = mapper.update(board);
		log.info("Update count: " + count);
	}
}
