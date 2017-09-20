var COMMON_URL = "http://mywechatmanage.duapp.com";

//微信js-sdk相关参数
var WECHAT_APPID = "wx02611549a85e2673"; // 必填，公众号的唯一标识
var TIMESTAMP = ""; // 必填，生成签名的时间戳
var NONCESTR = ""; // 必填，生成签名的随机串
var SIGNATURE = ""; // 必填，签名，见附录1
var SHARE_URL = "";

//获取地址栏传递的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if(r != null) return unescape(r[2]);
	return null; //返回参数值
}