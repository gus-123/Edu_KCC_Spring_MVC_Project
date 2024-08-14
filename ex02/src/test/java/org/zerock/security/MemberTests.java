package org.zerock.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
  "file:src/main/webapp/WEB-INF/spring/root-context.xml",
  "file:src/main/webapp/WEB-INF/spring/security-context.xml"
  })
@Log4j
public class MemberTests {

  @Setter(onMethod_ = @Autowired)
  private PasswordEncoder pwencoder;
  
  @Setter(onMethod_ = @Autowired)
  private DataSource ds;
  
  /*
  // MEMBER를 INSERT용
  @Test
  // tbl_member에 사용자를 생성하는 TEST 겸 & 데이터 값 추가
  public void testInsertMember() {

    String sql = "insert into tbl_member(userid, userpw, username) values (?,?,?)";
    
    for(int i = 0; i < 100; i++) {
      
      //JDBC 형식으로 MEMBER를 INSERT 해줌.
      Connection con = null;
      PreparedStatement pstmt = null;
      
      try {
        con = ds.getConnection();
        pstmt = con.prepareStatement(sql);
        
        // 패스워드를 인코딩해서 넣어 줌.
        pstmt.setString(2, pwencoder.encode("pw" + i));
        
        if(i <80) {
          
          pstmt.setString(1, "user"+i);
          pstmt.setString(3,"일반사용자"+i);
          
        }else if (i <90) {
          
          pstmt.setString(1, "manager"+i);
          pstmt.setString(3,"운영자"+i);
          
        }else {
          
          pstmt.setString(1, "admin"+i);
          pstmt.setString(3,"관리자"+i);
          
        }
        
        pstmt.executeUpdate();
        
      }catch(Exception e) {
        e.printStackTrace();
      }finally {
        if(pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
        if(con != null) { try { con.close();  } catch(Exception e) {} }
        
      }
    }//end for
  }
  */
  
  // Auth를 위한 INSERT용
  @Test
  public void testInsertAuth() {
    
    
    String sql = "insert into tbl_member_auth (userid, auth) values (?,?)";
    
    for(int i = 0; i < 100; i++) {
      
      Connection con = null;
      PreparedStatement pstmt = null;
      
      try {
        con = ds.getConnection();
        pstmt = con.prepareStatement(sql);
      
        
        if(i <80) {
          
          pstmt.setString(1, "user"+i);
          pstmt.setString(2,"ROLE_USER");
          
        }else if (i <90) {
          
          pstmt.setString(1, "manager"+i);
          pstmt.setString(2,"ROLE_MEMBER");
          
        }else {
          
          pstmt.setString(1, "admin"+i);
          pstmt.setString(2,"ROLE_ADMIN");
          
        }
        
        pstmt.executeUpdate();
        
      }catch(Exception e) {
        e.printStackTrace();
      }finally {
        if(pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
        if(con != null) { try { con.close();  } catch(Exception e) {} }
        
      }
    }//end for
  }

  
}


