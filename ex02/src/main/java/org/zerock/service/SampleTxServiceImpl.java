package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.Sample1Mapper;
import org.zerock.mapper.Sample2Mapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService{
	
	@Autowired
	private Sample1Mapper mapper1;
	
	@Autowired
	private Sample2Mapper mapper2;
	
	@Transactional  // 이걸로 트랜잭션 처리 할시 - 그래서 둘다 실패하게 됨. - autocommit(false)가 남. 그래서 rollback 가능
	@Override
	public void addData(String data) {
		log.info("mapper1.............");  // @Transactional 없을시 하나는 성공하고
		mapper1.insertCol1(data);  //autocommit이 되어버려서
		
		log.info("mapper2.............");  // @Transactional 없을시 하나는 실패함.
		mapper2.insertCol2(data);  //rollback이 적용되지 않음.
	}
}
