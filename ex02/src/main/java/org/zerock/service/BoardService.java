package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

//확장성을 확보하기 위한 중요한 설계 전략 (즉, 설계도 역할)
public interface BoardService {
	public void register(BoardVO  board);
	// public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri);
	public BoardVO get(Long bno);
	public boolean modify(BoardVO board);
	public boolean remove(Long bno);
	public int getTotal(Criteria cri);  // MyBatis에서 전체 데이터의 개수 처리
}
