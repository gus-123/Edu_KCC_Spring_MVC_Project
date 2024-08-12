console.log("reply......"); // replyService가 처음 호출될 때 실행되는 코드

// 함수의 실행과 함께 실행 됨.
// replyService 객체가 들어가는데 속성으로  add함수가 들어가서 리턴 됨.
let replyService = (function() {
  function add(reply, callback, error) {
    console.log("reply.....");

    // 서버로 댓글 정보 전송 (POST 요청)
    $.ajax({
      type: 'POST',
      url: '/replies/new',
      data: JSON.stringify(reply), // 자바스크립트 객체를 json 문자열로 전달하기 위해 stringify를 사용 함.
      contentType: "application/json; charset=utf-8",
      success: function(result) {
        // 댓글 추가 성공 시 콜백 함수 실행
        if (callback) {
          callback(result);
        }
      },
      error: function(xhr, status, er) {
        // 댓글 추가 실패 시 에러 콜백 함수 실행
        if (error) {
          error(er);
        }
      }
    });
  }

  function getList(param, callback, error) {

		var bno = param.bno;
		var page = param.page || 1;

		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
				function(data) {
					if (callback) {
						callback(data);
					}
				}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}
		});
	}
     
   function remove(rno, callback, error) {
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}

	function update(reply, callback, error) {

		console.log("RNO: " + reply.rno);

		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}

  function get(rno, callback, error) {

		$.get("/replies/" + rno, function(result) {

			if (callback) {
				callback(result);
			}

		}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}
		});
	}
	
	function displayTime(timeValue) {

		var today = new Date();

		var gap = today.getTime() - timeValue;

		var dateObj = new Date(timeValue);
		var str = "";

		if (gap < (1000 * 60 * 60 * 24)) {

			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();

			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
					':', (ss > 9 ? '' : '0') + ss ].join('');

		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
			var dd = dateObj.getDate();

			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
					(dd > 9 ? '' : '0') + dd ].join('');
		}
	};
  

  // 외부에서 사용할 수 있도록 add 함수를 반환
  return { //객체
    add: add,
    getList:getList,
    remove:remove,
    update:update,
    get:get,
    displayTime: displayTime
  };
})();
