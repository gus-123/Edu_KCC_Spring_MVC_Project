package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;

// 영속 계층
public interface Sample1Mapper {
	
	@Insert("insert into tbl_sample1 (col1) values (#{data})")
	public int insertCol1(String data);

}
