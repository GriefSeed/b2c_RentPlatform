var PHONE_IP = 'http://192.168.56.1:8080/ssm';

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
//订单状态转中文
function headerStatusTrans(s) {
	if(s == 'NEW')
		return "新建";
	if(s == 'USING')
		return "已配送，正在使用";
	if(s == 'DEBT')
		return "欠费";
	if(s == 'CLOSE')
		return "已关闭";
}

function nvl(obj1, obj2) {
	if(obj1 == undefined) {
		return obj2;
	} else {
		return obj1;
	}
}

//直接存储进LocalStorage里，itemCart为索引表
function itemInLocalSto(obj, model) {
	if(model == "add") {
		localStorage.setItem("item_" + obj.itemId, JSON.stringify(obj));
		localStorage.setItem("itemCart", nvl(localStorage.itemCart, "") + "item_" + obj.itemId + "|");
	}
	if(model == "delete") {
		localStorage.removeItem("item_" + obj.itemId);
		localStorage.setItem("itemCart", localStorage.itemCart.replace("item_" + obj.itemId + "|", ""));
	}
	if(model == "get") {
		if(localStorage.getItem("item_" + obj.itemId) == null)
			return null;
		else
			return JSON.parse(localStorage.getItem("item_" + obj.itemId));
	}
	if(model == "check") {
		return nvl(localStorage.getItem("itemCart"), "").indexOf("item_" + obj.itemId);
	}
	return "error";
}

//处理数据库的datetime
function timeFormatter(value) {
	var da = new Date(value);
	return da.getFullYear() + "-" + (da.getMonth() + 1) + "-" + da.getDate();
	//+ " " + da.getHours() + ":" + da.getMinutes() + ":" + da.getSeconds();

}