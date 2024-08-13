package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.ProductVO;

public interface ProductMapper {
	public int productAdd(ProductVO product);
	public List<ProductVO> getList();
}
