package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardAttachVO;

//첨부파일 처리를 위한 Mapper
public interface BoardAttachMapper {
	
	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByBno(Long bno);

}
