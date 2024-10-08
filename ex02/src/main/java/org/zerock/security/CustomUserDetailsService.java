package org.zerock.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;
import org.zerock.domain.CustomUser;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
// 위 코드 중요!!
public class CustomUserDetailsService implements UserDetailsService {

   @Setter(onMethod_ = { @Autowired })
   private MemberMapper memberMapper;

   @Override
   // 로그인 하면  loadUserByUsername 호출됨.
   // ID, PW, Auth를 'userName'에 전달 받음.
   public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

      log.warn("Load User By UserName : " + userName);

      // userName means userid
      // DB로 부터 ID, PW, Auth를 가져와 vo 객체를 가져왔기때문에 null이 아니면 로그인 성공을 의미함. - security가 판단.
      MemberVO vo = memberMapper.read(userName); 

      log.warn("queried by member mapper: " + vo);

      // MemberVO의 인스턴스를 얻을 수 있다면 CustomUser 타입의 객체로 변환해서 반환
      return vo == null ? null : new CustomUser(vo); //userDetail로 리턴
   } 

}
