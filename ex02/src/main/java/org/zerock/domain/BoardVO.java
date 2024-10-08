package org.zerock.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
//BOARD 테이블로 부터 읽어오는 용도
public class BoardVO {

  private Long bno;
  private String title;
  private String content;
  private String writer;
  private Date regdate;
  private Date updateDate;
  
  private int replyCnt;  // 댓글의 숫자를 의미하는 인스턴스 변수
  
//기존의 BoardVO는  등록 시 한 번에 BoardAttachVO를 처리할 수 있도록 List<BoardAttachVO>를 추가
  private List<BoardAttachVO> attachList;
}
