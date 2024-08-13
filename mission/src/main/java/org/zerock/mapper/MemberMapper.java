package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.MemberVO;

public interface MemberMapper {
	public int memberRegister(MemberVO member);
	public MemberVO findMemberById(String id);
	public List<String> findAllId();
	public MemberVO findByIdAndPassword(String param1, String param2);
}
