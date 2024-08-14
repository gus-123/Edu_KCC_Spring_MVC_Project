<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- all or member or admin -->
<h1>/sample/all page</h1>


<sec:authorize access="isAnonymous()"> <!--  모두가 볼수 있음 -->

  <a href="/customLogin">로그인</a>

</sec:authorize>

<sec:authorize access="isAuthenticated()"> <!-- 인증한 놈만 볼수 있음 -->

  <a href="/customLogout">로그아웃</a>

</sec:authorize>

</body>
</html>
