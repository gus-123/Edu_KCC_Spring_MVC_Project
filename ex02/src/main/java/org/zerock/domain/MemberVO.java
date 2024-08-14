package org.zerock.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
// 회원가입하면 한사람에 대한 정보를 가지기 위한 용도
// 여러 개의 사용자 권한을 가질 수 있는 구조로 설계
public class MemberVO {

	private String userid;
	private String userpw;
	private String userName;
	private boolean enabled;

	private Date regDate;
	private Date updateDate;
	private List<AuthVO> authList;

}
