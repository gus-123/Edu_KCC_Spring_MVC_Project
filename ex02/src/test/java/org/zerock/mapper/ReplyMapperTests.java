package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//Spring Container의 context를 가져오기 위한 용도(즉,  테스트를 실행하기 위한 환경을 설정)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	@Autowired
	private ReplyMapper mapper;
	
	// 현재 존재하는 댓글의 배열 생성
	private Long[] bnoArr = {15L, 16L, 17L, 18L, 19L};
	
	// mapper가 작동하는 지 확인 용
//	@Test
//	public void test() {
//		log.info(mapper);
//	}
	
	// 댓글 만드는 용도 insert TEST
//	@Test
//	public void testCreate() {
//		IntStream.rangeClosed(1, 10).forEach(i -> {
//			ReplyVO vo = new ReplyVO();
//			vo.setBno(bnoArr[i%5]);
//			vo.setReply("댓글 테스트" + i);
//			vo.setReplyer("reply" + i);
//			
//			mapper.insert(vo);
//		});
//	}
	
	// 댓글 하나를 가져오는 용도 TEST
//	@Test
//	public void testRead() {
//		Long tatgetRno = 2L;
//		ReplyVO vo = mapper.read(tatgetRno);
//		log.info(vo);
//	}
	
	// 댓글 하나를 삭제하는 용도 TEST
//	@Test
//	public void testDelete() {
//		Long tatgetRno = 2L;
//		mapper.delete(tatgetRno);
//	}
	
	// 댓글 하나를 수정하는 용도 TEST
//	@Test
//	public void testUpdate() {
//		Long tatgetRno = 3L;
//		ReplyVO vo = mapper.read(tatgetRno);
//		vo.setReply("Update Reply...");
//		
//		int count = mapper.update(vo);
//		log.info("update count: " + count);
//	}
	
	// @param을 이용하면 두개 값을 받을 수 있음.
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		replies.forEach(reply -> log.info(reply));
	}
}
