<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Upload with Ajax</h1>



	<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}
</style>

<style>
.bigPictureWrapper {
  position: absolute;
  display: none;
  justify-content: center;
  align-items: center;
  top:0%;
  width:100%;
  height:100%;
  background-color: gray; 
  z-index: 100;
}

.bigPicture {
  position: relative;
  display:flex;
  justify-content: center;
  align-items: center;
}
</style>

<div class='bigPictureWrapper'>
  <div class='bigPicture'>
  </div>
</div>


	<div class='uploadDiv'>
		<input type='file' name='uploadFile' multiple>
	</div>

	<div class='uploadResult'>
		<ul>

		</ul>
	</div>


	<button id='uploadBtn'>Upload</button>

	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>

	<script type ="text/javascript" >
		// 기본 파일 업로드
		$(function() {
			
			var cloneObj = $(".uploadDiv").clone(); // 클래스 이름이 "uploadDiv"인 모든 요소를 선택 및 선택된 요소를 복제하여 새로운 요소를 생성
			
        	$("#uploadBtn").on("click", function(e) {

				var formData = new FormData();
           		var inputFile = $("input[name='uploadFile']");
           		var files = inputFile[0].files;

           		console.log(files);

           		for (var i = 0; i < files.length; i++) {
           			if (!checkExtension(files[i].name, files[i].size)) {  // 파일이름과 파일 사이즈를 checkExtension 함수로 검사
    					return false;
    				}
           			
           			formData.append("uploadFile", files[i]);
           		}

           		$.ajax({
              		url : '/uploadAjaxAction',
              		processData : false,
              		contentType : false,
              		data : formData,
              		type : 'POST',
              		success : function(result) {
                 		//alert("Uploaded");
                 		showUploadedFile(result); //
                 		$(".uploadDiv").html(cloneObj.html())  // 파일 이름 출력을 html 띄움.
              		}
           		}); //$.ajax 
			}); // end on
			
			// 파일 이름 출력
			var uploadResult = $(".uploadResult ul");
			
			function showUploadedFile(uploadResultArr) {
				var str = "";
				$(uploadResultArr).each(function(i, obj) {
					str += "<li>" + obj.fileName + "</li>";  // AttachFileDTO의 fileName값을 통해서 출력이 가능 함.
				});
				
				uploadResult.append(str);
			} 
		}); // end main
		
		// 파일 업로드 상세 처리
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
        var maxSize = 5242880; //5MB
        function checkExtension(fileName, fileSize) {

           if (fileSize >= maxSize) {
              alert("파일 사이즈 초과");
              return false;
           }

           if (regex.test(fileName)) {
              alert("해당 종류의 파일은 업로드할 수 없습니다.");
              return false;
           }
           return true;
        }
	</script>


</body>
</html>
