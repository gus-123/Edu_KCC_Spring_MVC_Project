package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data  //getter, setter를 자동으로 생성해주기 위해 lombok을 사용하는 것임.
public class Chef {
	private String name;

}
