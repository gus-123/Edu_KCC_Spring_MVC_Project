package org.zerock.domain;

import lombok.Data;

@Data
// 첨부파일을 보관하는 테이블인 'tbl_attach' 테이블로 부터 읽어오는 용도
public class BoardAttachVO {
	
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	
	private Long bno;  // tbl_board와 외래키의 관계를 가짐.

}
