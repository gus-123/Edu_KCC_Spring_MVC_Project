<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.9.1/jquery.min.js" integrity="sha512-jGR1T3dQerLCSm/IGEGbndPwzszJBlKQ5Br9vuB0Pw2iyxOy+7AK+lJcCC8eaXyz/9du+bkCy4HXxByhxkHf+w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .signup-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 500px;
        }
        .signup-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 95%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-group input:focus {
            border-color: #007bff;
            outline: none;
        }
        .signup-button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .signup-button:hover {
            background-color: #0056b3;
        }
        .signup-footer {
            text-align: center;
            margin-top: 10px;
        }
    </style>
    
    <script type="text/javascript">
    	$(function() {
    		$('input[name=id]').blur(function () {
	    		$.ajax({
	    			url: '/mission/user/find',
	    			type : 'GET',
	    			dataType: 'json',
	    			success: function(data) {
	    				$.each(data, function(index, item) {
		    				let id = $('input[name=id]').val();
	    					if(item === id) {
	    						alert('중복된아이디입니다.');
	    						$('input[name=id]').val('');
	    					}	
	    				})
	    			}	
	    		})
    			
    		})
    	})
    </script>
</head>
<body>

<div class="signup-container">
    <h2>회원가입</h2>
    <form action="/mission/user/join" method="post">
        <div class="form-group">
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" required>
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="birthday">생일</label>
            <input type="date" id="birth" name="birth" required>
        </div>
        <button type="submit" class="signup-button">가입하기</button>
    </form>
    <div class="signup-footer">
        <p>이미 계정이 있으신가요? <a href="/mission/user/login">로그인</a></p>
    </div>
</div>

</body>
</html>
