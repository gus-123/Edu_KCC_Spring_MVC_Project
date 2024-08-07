package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//VO 객체 vs DTO객체
// DB에 있는 데이터의 값을 읽고 쓰기를 할때는 VO 객체
// 레이어끼리 데이터를 객체화 시켜주어야 할때 DTO 객체를 사용
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleDTO {
	private String name;
	private int age;
}
