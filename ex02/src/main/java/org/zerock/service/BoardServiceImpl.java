package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService {

   @Autowired
   private BoardMapper mapper;
   
   @Override
   public void register(BoardVO board) {
      log.info("*****************************************************");
      log.info("register..............");
      log.info("*****************************************************");
      mapper.insertSelectKey(board);
   }
   
//   @Override
//   public List<BoardVO> getList() {
//      log.info("*****************************************************");
//      log.info("getList()..............");
//      log.info("*****************************************************");
//      return mapper.getList();
//   }
   
   @Override
   public List<BoardVO> getList(Criteria cri) {
	   log.info("*****************************************************");
	   log.info("get List with criteria: " + cri);
	   log.info("*****************************************************");
	   return mapper.getListWithPaging(cri);
   }

   @Override
   public BoardVO get(Long bno) {
      log.info("*****************************************************");
      log.info("read..............");
      log.info("*****************************************************");
      return mapper.read(bno);
   }

   @Override
   public boolean modify(BoardVO board) {
      log.info("*****************************************************");
      log.info("update..............");
      log.info("*****************************************************");
      return mapper.update(board) == 1;
   }

   @Override
   public boolean remove(Long bno) {
      log.info("*****************************************************");
      log.info("delete..............");
      log.info("*****************************************************");
      return mapper.delete(bno) == 1;
   }
   
   @Override
   public int getTotal(Criteria cri) {
	   
	   log.info("get total count");
	   return mapper.getTotalCount(cri);
   }
   
}
