
$('#modify-content').on('click',function(){
	/* 
		로그인한 회원의 아이디와 글쓴이가 같을 경우 수정/삭제 버튼이 출력되므로
		여기에선 따로 id값 비교 하지않음
	*/
/*	console.log("###test boardNo:"+boardNo);
	console.log("###test memberNo:"+memberNo);
	console.log("###test memberId:"+memberId);*/
	console.log("###test boardNo:"+$('#memberNo').val());
	var $content = $('#content-text');
	var $button = $('#modify-content');
	var editableStatus = $content.is("[contenteditable='true']");
	
	$button.toggleClass('btn-success btn-primary');
	$content.attr('contenteditable',!editableStatus);
	if(!editableStatus){
		$content.focus();
		$button.text('확인');
	}else{
	 	var data = {
	 			boardNo	: $('#boardNO').val(),
	 	 		content		: $content.text() 
		} 
		console.log(data);
		$.ajax({
			type: 'post',
			url: '/kcy/modifyPosting',
			data : JSON.stringify(data),
			context : this,
			contentType: 'application/json; charset=UTF-8',
			success: function (response) {
				$(this).parents('.card').remove();
		      loadBookmark();
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		        console.log("### modify-content : Ajax ERROR ###");
		    }
		}) 
		$button.text('수정');
	}
	

	
});
