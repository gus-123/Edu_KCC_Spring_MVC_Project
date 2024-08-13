package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.ProductVO;
import org.zerock.mapper.ProductMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Service
@AllArgsConstructor
@Log4j
public class ProductServiceImpl implements ProductService {

	private ProductMapper mapper;
	
	@Override
	public int productAdd(ProductVO product) {
		return mapper.productAdd(product);
	}

	@Override
	public List<ProductVO> getList() {
		return mapper.getList();
	}

}
