package org.zerock.service;

import org.springframework.stereotype.Service;
import org.zerock.domain.CartVO;
import org.zerock.mapper.CartMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class CartServiceImpl implements CartService{
	
	private CartMapper mapper;

	@Override
	public int cartAdd(CartVO cart) {
		return mapper.cartAdd(cart);
	}
	
}
