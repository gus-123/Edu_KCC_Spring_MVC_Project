<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.9.1/jquery.min.js" integrity="sha512-jGR1T3dQerLCSm/IGEGbndPwzszJBlKQ5Br9vuB0Pw2iyxOy+7AK+lJcCC8eaXyz/9du+bkCy4HXxByhxkHf+w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<title>Insert title here</title>
<style>
	body {
    font-family: 'Arial', sans-serif;
    background-color: #f8f8f8;
    margin: 0;
    padding: 20px;
}

.main_container {
    width: 800px;
    margin: 0 auto;
    background-color: #ffffff;
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    padding: 20px;
}

.main_header {
    text-align: right;
    margin-bottom: 30px;
}

.main_header a {
    margin: 0 10px;
    text-decoration: none;
    color: #007bff;
    font-weight: bold;
}

.main_header a:hover {
    text-decoration: underline;
}

.main_section {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.product_box {
    flex: 0 0 43%; 
    margin: 10px 0;
    border: 1px solid #eaeaea;
    border-radius: 10px;
    padding: 20px;
    background-color: #fafafa;
    transition: transform 0.2s;
}

.product_box:hover {
    transform: scale(1.02); /* 마우스 오버 시 확대 효과 */
}

.pimage {
    display: flex;
    justify-content: center;
    margin-bottom: 15px;
}

.pimage img {
    width: 150px;
    height: 150px;
    border-radius: 10px; /* 이미지 모서리 둥글게 */
}

.pname, .price, .count_area, .button_area {
    font-weight: bold;
    margin-bottom: 15px;
    font-size: 1.2rem;
    text-align: center;
}

.price {
    color: #28a745; /* 가격 색상 변경 */
}

.count_area button, .button_area button {
    width: 40px;
    height: 40px;
    font-size: 1.5rem;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s;
}

.count_area button:hover, .button_area button:hover {
    background-color: #0056b3; /* 버튼 호버 색상 */
}

.count_area input {
    width: 60px;
    text-align: center;
    font-size: 1.2rem;
    margin: 0 5px;
}

.button_area .add_cart_btn {
	width: 80px;
	font-size: 1.2rem;
}

.button_area {
    margin-top: 10px;
}

	
</style>

<script type="text/javascript">
	$(function (){
		$('body').on('click', '.add_cart_btn', function (){
			let productVO = 
				[
					{
						pno : $(this).parents('.product_box').data('pno'),
						pimage : $(this).parents('.product_box').find('img').attr('src'),
						pname : $(this).parents('.product_box').find('.pname').text(),
						price : parseInt($(this).parents('.product_box').find('.price').text().replace(/,/g , ''))
					}
				];
			$.ajax({
				url : '/mission/main',
				type : 'POST',
				data : JSON.stringify(productVO),
				contentType:"application/json; charset:UTF-8",
				success : function(data) {
					alert(data);
					console.log(data);
				},
				error : function (error) {
					console.log(error);
				}
			})
			
		})
		
	});
</script>

</head>

<body>

<div class="main_container">
<header class="main_header">
<c:choose>
	<c:when test="${member==null }">
		<a href="/mission/user/login" >로그인</a>	
		<a href="/mission/user/join">회원가입</a>
	</c:when>
	<c:when test="${member !=null }">
		<a href="/mission/user/logout">로그아웃</a>	
		<a href="/mission/cart">장바구니</a>
	</c:when>
</c:choose>
</header>
<section class="main_section">
<c:forEach items="${list}" var="product">
	<div class="product_box" data-pno=${product.pno}>
		<div class="pimage">
			<img alt="${product.pname}" src="${product.pimage }">
		</div>
		<div class="pname">${product.pname}</div>
		<div class="price"><fmt:formatNumber value="${product.price}" pattern="#,##0" /><span class="text">원</span></div>
		<div class="button_area">
			<button class="add_cart_btn">추가</button>
		</div>
	</div>
</c:forEach>
	
</section>
</div>


</body>
</html>