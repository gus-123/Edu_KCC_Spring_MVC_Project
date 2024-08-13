package org.zerock.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
	private String id;
	private String name;
	private String password;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;
}


