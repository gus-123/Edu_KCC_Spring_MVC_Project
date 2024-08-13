<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	
	.cart_container {
	    width: 800px;
	    margin: 0 auto;
	    background-color: #ffffff;
	    border-radius: 10px;
	    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	    padding: 20px;
	}
	
	.cart_header {
	    text-align: center;
	    margin-bottom: 20px;
	    font-size: 2rem;
	    font-weight: bold;
	}
	
	.cart_item {
	    display: flex;
	    justify-content: space-between;
	    align-items: center;
	    padding: 15px;
	    border-bottom: 1px solid #eaeaea;
	}
	
	.cart_item:last-child {
	    border-bottom: none; /* 마지막 항목의 경계선 제거 */
	}
	
	.cart_item img {
	    width: 100px;
	    height: 100px;
	    border-radius: 10px;
	}
	
	.item_details {
	    flex: 1;
	    margin-left: 15px;
	}
	
	.item_name {
	    font-size: 1.5rem;
	    font-weight: bold;
	}
	
	.item_price {
	    color: #28a745; /* 가격 색상 */
	    font-size: 1.2rem;
	}
	
	.quantity_area {
	    display: flex;
	    align-items: center;
	}
	
	.quantity_area input {
	    width: 50px;
	    text-align: center;
	    margin: 0 5px;
	    font-size: 1rem;
	}
	
	.quantity_area button {
	    width: 30px;
	    height: 30px;
	    font-size: 1.5rem;
	    border: none;
	    border-radius: 5px;
	    background-color: #007bff;
	    color: white;
	    cursor: pointer;
	    transition: background-color 0.3s;
	}
	
	.quantity_area button:hover {
	    background-color: #0056b3;
	}
	
	.total_area {
	    margin-top: 20px;
	    font-size: 1.5rem;
	    text-align: right;
	}
	
	.checkout_button {
	    display: block;
	    width: 100%;
	    padding: 15px;
	    background-color: #28a745;
	    color: white;
	    border: none;
	    border-radius: 5px;
	    font-size: 1.5rem;
	    cursor: pointer;
	    transition: background-color 0.3s;
	    margin-top: 20px;
	}
	
	.checkout_button:hover {
	    background-color: #218838;
	}
	
</style>
<script type="text/javascript">
	$(function () {
		 
		$('body').on('click', '.quantity_area>:nth-child(n)', function (){
			
			let btn_class = $(this).attr('class');
			let count = $(this).siblings('input').val();
			const price = parseInt($(this).parents('.cart_item').find('.item_price').data('price'));
			
			if(btn_class === "plus_btn") {
				count++;
				$(this).siblings('input').val(count);
				$(this).parents('.cart_item').find('.item_price').text((price * count).toLocaleString() + '원');

			} else if(btn_class === "minus_btn") {
				if(count >1) {
					count--;
					$(this).siblings('input').val(count);
					$(this).parents('.cart_item').find('.item_price').text((price * count).toLocaleString() + '원');

				} else{
					alert('최소한 1개 이상은 있어야 합니다.');
				}
			} else {
				return;
			}
			
			let prices = $('.item_price').text().replace(/,/g , '').split('원');
			let total = 0;
			for(let i =0 ; i<prices.length; i++) {
				console.log(prices[i]);
				if(prices[i] !== '') {
					total +=parseInt(prices[i]);
				}
			}
			$('#totalPrice').text(total.toLocaleString() + ' 원');
			 
		})
		
		$('body').on('click', '.checkout_button', function () {
			/*  const cartVO = []; */
			    $('.cart_item').each(function() {
			        const pno = $(this).data('pno');
			        const cnt = parseInt($(this).find('.quantity_input').val());
			        const price = parseInt($(this).find('.item_price').data('price'));
				    const id = $('input[name="memberId"]').val();

			       /*  cartVO.push({
			            pno: pno,
			            cnt: cnt,
			            totalPrice: price * cnt,
			            id : id
			        }); */
				    $.ajax({
				        type: 'POST',
				        url: '/mission/cart', // 컨트롤러의 URL
				        contentType: 'application/json',
				        data: JSON.stringify({
				        	pno: pno,
				            cnt: cnt,
				            totalPrice: price * cnt,
				            id : id
				        }),
				        success: function(response) {
				            alert("결제 완료!!!");
				            location.href = '/mission/main';
				        },
				        error: function(xhr, status, error) {
				            alert('결제에 실패했습니다. 다시 시도해 주세요.');
				        }
				    });
			    });
	
			    
		})
		
	})
</script>
</head>
<body>

<div class="cart_container">
    <header class="cart_header">장바구니</header>
  
    <c:forEach items="${products }" var="product">
    
	    <div class="cart_item" data-pno="${product.pno}">
	    <input type="hidden" value="${member.id}"  name="memberId">
	        <img src="${product.pimage }" alt="${product.pname }">
	        <div class="item_details">
	            <div class="item_name">${product.pname }</div>
	            <div class="item_price" data-price="${product.price}"><fmt:formatNumber value="${product.price}" pattern="#,##0" />원</div>
	        </div>
	        <div class="quantity_area">
	            <button class="minus_btn">-</button>
	            <input type="text" value="1" class="quantity_input">
	            <button class="plus_btn">+</button>
	        </div>
	    </div>
    </c:forEach>



    <div class="total_area">총 금액: <span id="totalPrice">3,000 원</span></div>
    <button class="checkout_button">결제하기</button>
</div>

</body>
</html>