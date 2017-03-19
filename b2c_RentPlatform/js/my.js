var PHONE_IP = 'http://192.168.0.108:8080/ssm';

var add = function(a, b) {
	alert(this) //this被绑顶到window
	return a + b;
}

// 获取url中的参数,name表示你要取的值在url中的命名，如....？type=...，name就是type
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) {
		return unescape(r[2]);
	} else {
		return null;
	}
}