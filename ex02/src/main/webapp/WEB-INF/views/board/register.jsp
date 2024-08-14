<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Register</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

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

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">Board Register</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

        <form role="form" action="/board/register" method="post">
          <div class="form-group">
            <label>Title</label> <input class="form-control" name='title'>
          </div>

          <div class="form-group">
            <label>Text area</label>
            <textarea class="form-control" rows="3" name='content'></textarea>
          </div>

          <div class="form-group">
            <label>Writer</label> <input class="form-control" name='writer'>
          </div>
          <button type="submit" class="btn btn-default">Submit
            Button</button>
          <button type="reset" class="btn btn-default">Reset Button</button>
        </form>

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<!-- 첨부파일 등록을 위한 화면 처리 -->
<div class="row">
	<div class="col-lg-12">
    	<div class="panel panel-default">

      		<div class="panel-heading">File Attach</div>
      		<!-- /.panel-heading -->
      		<div class="panel-body">
        	<div class="form-group uploadDiv">
            	<input type="file" name='uploadFile' multiple>
        	</div>
        
        	<div class='uploadResult'> 
          	<ul>
          
          	</ul>
        	</div>
        
        
			</div>
    		<!--  end panel-body -->

    	</div>
    	<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->

<script>

	// 'Submit Button'을 클릭했을 때 첨부파일 관련된 처리
	$(document).ready(function(e){
		/*  
		// 'Submit Button'을 클릭했을 때 첨부파일 관련된 처리
		var formObj = $("form[role='form']");
	    $("button[type='submit']").on("click", function(e){
	    	
	    	e.preventDefault();
	    
    		console.log("submit clicked");
        }); 
	    */
	    
	    // <input type ='hidden'>으로 처리된 첨부파일의 정보는 BoardVO 로 수집
	    // BoardVO에는 attachList라는 이름의 변수로 첨부파일의 정보를 수집하기 때문에 <input type ='hidden'>의
	    // name은 'attachList[인덱스번호]'와 같은 이름을 사용하도록 함.
	    var formObj = $("form[role='form']");
	    
	    $("button[type='submit']").on("click", function(e){
	      
	      e.preventDefault();
	      
	      console.log("submit clicked");
	      
	      var str = "";
	      
	      $(".uploadResult ul li").each(function(i, obj){
	        
	        var jobj = $(obj);
	        
	        console.dir(jobj);
	        console.log("-------------------------");
	        console.log(jobj.data("filename"));
	        
	        
	        str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
	        str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
	        str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
	        str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";
	        
	      });
	      
	      console.log(str);
	      
	      formObj.append(str).submit();
	      
	    });
	    
	    
	    // 파일의 업로드는 별도의 업로드 버튼을 두지 않고, <input type='file'>의 내용이 변경되는 것을 감지해서 처리
	    var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	    var maxSize = 5242880; //5MB
	    
	    function checkExtension(fileName, fileSize){
	      
	      if(fileSize >= maxSize){
	        alert("파일 사이즈 초과");
	        return false;
	      }
	      
	      if(regex.test(fileName)){
	        alert("해당 종류의 파일은 업로드할 수 없습니다.");
	        return false;
	      }
	      return true;
	    }
	    
	    $("input[type='file']").change(function(e){

	      var formData = new FormData();
	      
	      var inputFile = $("input[name='uploadFile']");
	      
	      var files = inputFile[0].files;
	      
	      for(var i = 0; i < files.length; i++){

	        if(!checkExtension(files[i].name, files[i].size) ){
	          return false;
	        }
	        formData.append("uploadFile", files[i]);
	        
	      }
	      
	      $.ajax({
	        url: '/uploadAjaxAction',
	        processData: false, 
	        contentType: false,
	  	  data: formData,
	  	  type: 'POST',
	        dataType:'json',
	          success: function(result){
	            console.log(result); 
	  		  showUploadResult(result); //업로드 결과 처리 함수 

	        }
	      }); //$.ajax
	      
	    });
	    
	    // 업로드된 결과를 화면에 섬네일 등을 만들어서 처리하는 부분
	    function showUploadResult(uploadResultArr){
		    
	        if(!uploadResultArr || uploadResultArr.length == 0){ return; }
	        
	        var uploadUL = $(".uploadResult ul");
	        
	        var str ="";
	        
	        $(uploadResultArr).each(function(i, obj){
	        
	            /*
	            //image type - 이미지 파일인 경우와 일반 파일의 경우에 보여지는 화면의 내용은 HTML 태그들을 이용해서 작성됨.
	            // 삭제를 위해 업로드된 파일의 경로와 UUID가 포함된 파일 이름이 필요하여 위의 코드 수정 버전
				// <button> 태그에 'data-file'과 'data-type' 정보를 추가 함.
	            if(obj.image){
	              var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
	              str += "<li><div>";
	              str += "<span> "+ obj.fileName+"</span>";
	              str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
	              str += "<img src='/display?fileName="+fileCallPath+"'>";
	              str += "</div>";
	              str +"</li>";
	            }else{
	              var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);            
	                var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
	                  
	              str += "<li><div>";
	              str += "<span> "+ obj.fileName+"</span>";
	              str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
	              str += "<img src='/resources/img/attach.png'></a>";
	              str += "</div>";
	              str +"</li>";
	            } 
	            */
	            
	    		//image type - 이미지 파일인 경우와 일반 파일의 경우에 보여지는 화면의 내용은 HTML 태그들을 이용해서 작성됨.
				// 게시물 등록과 첨부파일의 데이터 베이스 처리(p.562)
				// 이를 위해서 첨부파일 정보를 태그로 생성할 때 첨부파일과 관련된 정보(data-uuid, data-filename, data-type)룰 추가
	    		if(obj.image){
	    			var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
	    			str += "<li data-path='"+obj.uploadPath+"'";
	    			str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'"
	    			str +" ><div>";
	    			str += "<span> "+ obj.fileName+"</span>";
	    			str += "<button type='button' data-file=\'"+fileCallPath+"\' "
	    			str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
	    			str += "<img src='/display?fileName="+fileCallPath+"'>";
	    			str += "</div>";
	    			str +"</li>";
	    		}else{
	    			var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);			      
	    		    var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
	    		      
	    			str += "<li "
	    			str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' ><div>";
	    			str += "<span> "+ obj.fileName+"</span>";
	    			str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' " 
	    			str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
	    			str += "<img src='/resources/img/attach.png'></a>";
	    			str += "</div>";
	    			str +"</li>";
	    		}

	        });
	        
	        uploadUL.append(str);
	      }
		
	    // 첨부파일의 변경 처리 - 업로드된 파일의 삭제
	    // 'x' 아이콘을 클릭하면 서버에서 삭제하도록 이벤트를 처리
	    // 브라우저에서 첨부파일을 삭제하면 업로드된 파일도 같이 삭제 됨. - 'uploadAjax.jsp'와 다름.
	    $(".uploadResult").on("click", "button", function(e){
		    
	        console.log("delete file");
	          
	        var targetFile = $(this).data("file");
	        var type = $(this).data("type");
	        
	        var targetLi = $(this).closest("li");
	        
	        $.ajax({
	          url: '/deleteFile',
	          data: {fileName: targetFile, type:type},
	          dataType:'text',
	          type: 'POST',
	            success: function(result){
	               alert(result);
	               
	               targetLi.remove();
	             }
	        }); //$.ajax
	       });

	    
	    
	});
</script>

<%@include file="../includes/footer.jsp"%>
