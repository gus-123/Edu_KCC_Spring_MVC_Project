package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {

	public List<BoardVO> getList();

	public List<BoardVO> getListWithPaging(Criteria cri);  // Criteria객체를 이용해 페이징처리된 결과를 가져옴.

	public void insert(BoardVO board);

	public Integer insertSelectKey(BoardVO board);

	public BoardVO read(Long bno);

	public int delete(Long bno);

	public int update(BoardVO board);

	public int getTotalCount(Criteria cri);
	
	// 해당 게시물의 번호인 bno와 증가나  감소를 의미하는 amount 변수에 파라미터를 받을 수 있도록 처리
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
