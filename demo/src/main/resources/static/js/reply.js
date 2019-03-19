var replyManager = (function(){
	
var getAll = function (obj, callback) {
	console.log("getAll 메서드");
	$.getJSON('/reply/'+obj, callback)
};


var add = function(obj, callback) {
	console.log("add 메서드");
	
	$.ajax({
		type : 'post',
		url  : '/reply/' + obj.boardNo,
		data : JSON.stringify(obj),
		dataType : 'json',
		contentType : "application/json",
		success : callback
	});
};

var update = function(obj, callback) {
	console.log("update 메서드");
};

var remove = function (obj, callback) {
	console.log("remove 메서드");
};

return {
	getAll : getAll,
	add : add,
	update : update,
	remove : remove
}

})();