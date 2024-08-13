package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Transactional  // 댓글의 갯수와 게시판의 게시글의 수가 뜨는것의 트랜잭션의 특성인 일관성을 위해 어노테이션을 적용 
	@Override
	public int register(ReplyVO vo) {
		log.info("register......" + vo);
		
		// 댓글 추가시 사용 - 댓글이 추가 되기 때문에 게시글이 1씩 증가한다.
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		
		return mapper.insert(vo);
	}
	
	@Override
	public ReplyVO get(Long rno) {
		log.info("get......" + rno);
		
		return mapper.read(rno);
	}
	
	@Override
	public int modify(ReplyVO vo) {
		log.info("get......" + vo);
		
		return mapper.update(vo);
	}
	
	@Transactional  // 댓글의 갯수와 게시판의 게시글의 수가 뜨는것의 트랜잭션의 특성인 일관성을 위해 어노테이션을 적용
	@Override
	public int remove(Long rno) {
		log.info("remove......" + rno);
		
		ReplyVO vo = mapper.read(rno);
		
		// 댓글 삭제시 사용 - 댓글이 삭제 되기 때문에 게시글이 1씩 감소한다.
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("get Reply List of a Board " + bno);
		
		return mapper.getListWithPaging(cri, bno);
	}
}
