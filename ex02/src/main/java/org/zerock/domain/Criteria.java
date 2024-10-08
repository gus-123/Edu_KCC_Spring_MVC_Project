package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@Getter
// 페이징 처리 용도(개별 객체를 만들거나 해시 맵으로 데이터를 전달)
public class Criteria {

  private int pageNum;  // 페이지 번호
  private int amount;  //글을 몇개를 보여 줄것인가
  
  private String type;
  private String keyword;

  public Criteria() {
    this(1, 10);
  }

  public Criteria(int pageNum, int amount) {
    this.pageNum = pageNum;
    this.amount = amount;
  }
  
  public String[] getTypeArr() {
    
    return type == null? new String[] {}: type.split("");
  }
}
