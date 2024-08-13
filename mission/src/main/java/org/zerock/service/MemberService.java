package org.zerock.service;

import java.util.List;

import org.zerock.domain.MemberVO;

public interface MemberService {
	public int memberRegister(MemberVO member);
	public MemberVO findMemberById(String id);
	public List<String> findAllId();
	public MemberVO findByIdAndPassword(String id, String password);
}
