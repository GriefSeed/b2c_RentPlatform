var PHONE_IP = 'http://192.168.0.108:8080/ssm';
//购物车

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
//判断商品状态，0为可租，1为正在租，2为冻结不可用
function itemStatus(s) {
	if(s == 0)
		return "可租";
	if(s == 1)
		return "<font style='color:red'>已租</font>";
	if(s == 2)
		return "<font style='color:red'>已下架不可用</font>";
	return "error";
}

//将js对象添加到数组中，返回JSON数组，用于存入LocalStorage，实现增删
//str是localstorage里的存储对象，obj是要增删的对象，model=0为增，model=1为删,2为查找
function jsonToArr(str, obj, model) {
	var JSONObj = JSON.parse(str);
	if(model == 0)
		JSONObj.setItem(obj.itemId, obj.unitCost);
	if(model == 1)
		JSONObj.removeItem(obj.itemId);
	if(model == 2) {
		if(JSONObj.getItem(obj.itemId) != undefined)
			return true;
		else
			return false;
	}
	return Json.stringify(JSONObj);

}