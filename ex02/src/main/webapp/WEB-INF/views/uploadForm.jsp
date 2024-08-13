<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 파일 업로드 처리시 enctype="multipart/form-data" 이걸로 꼭 해주어야 됨. -->
<form action="uploadFormAction" method="post" enctype="multipart/form-data">

<input type='file' name='uploadFile' multiple>

<button>Submit</button>

</form>

</body>
</html>
