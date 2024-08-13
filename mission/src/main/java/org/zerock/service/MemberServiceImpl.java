package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class MemberServiceImpl implements MemberService {

	private MemberMapper mapper;
	
	@Override
	public int memberRegister(MemberVO member) {
		return mapper.memberRegister(member);
	}

	@Override
	public MemberVO findMemberById(String id) {
		return mapper.findMemberById(id);
	}

	@Override
	public List<String> findAllId() {
		return mapper.findAllId();
	}

	@Override
	public MemberVO findByIdAndPassword(String id, String password) {
		return mapper.findByIdAndPassword(id, password);
	}

}
