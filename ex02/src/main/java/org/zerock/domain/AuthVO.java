package org.zerock.domain;


import lombok.Data;

@Data
//회원가입하면 여러사람에 대한 권한 용도
//tbl_member_auth의 칼럼을 그대로 반영해서 userid, auth를 지정
public class AuthVO {

  private String userid;
  private String auth;
  
}
