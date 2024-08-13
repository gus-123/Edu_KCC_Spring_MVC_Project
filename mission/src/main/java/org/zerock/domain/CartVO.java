package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {
	private Long cno;
	private String id;
	private Long pno;
	private Long cnt;
	private Long totalPrice;
}
