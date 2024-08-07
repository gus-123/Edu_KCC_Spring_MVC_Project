package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component  /// 빈등록, 컴포넌트 스캔
@Data 
@AllArgsConstructor  // 생성자를 만들어줌.
@NoArgsConstructor // 기존 생성자를 만들어줌.
public class Restaurant {

	@Autowired
	private Chef chef;
}
