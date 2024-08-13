package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;

//영속 계층
public interface Sample2Mapper {
	
	@Insert("insert into tbl_sample2 (col2) values (#{data})")
	public int insertCol2(String data);

}
