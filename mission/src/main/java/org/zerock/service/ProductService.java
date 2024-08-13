package org.zerock.service;

import java.util.List;

import org.zerock.domain.ProductVO;

public interface ProductService {
	public int productAdd(ProductVO product);
	public List<ProductVO> getList();
}
