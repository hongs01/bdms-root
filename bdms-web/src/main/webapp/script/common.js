/*window.onerror = function() {return true;};*/
Math.randomInt = function(nMax) { return parseInt(Math.random()*nMax); };	// 生成 [0, nMax) 的随机整数
Math.isInt = function(s) {var re = /^[0-9]*$/; return re.test(s);};

Array.prototype.find = function(e) { for (var i=0; i<this.length; ++i) { if (this[i]==e) { return i; } } return null; };
Array.prototype.findmul = function(e) { var a = []; for (var i=0; i<this.length; ++i) { if (this[i]==e) { a.push(i); } } return a; };
Array.prototype.find_if = function(fnEQ) { for (var i=0; i<this.length; ++i) { if (fnEQ(this[i])) { return i; } } return null; };
Array.prototype.findmul_if = function(fnEQ) { var a = []; for (var i=0; i<this.length; ++i) { if (fnEQ(this[i])) { a.push(i); } } return a; };
Array.prototype.push_not = function(e) { if (this.find(e)==null) { return this.push(e); } return null; };
Array.prototype.push_not_if = function(e, fnEQ) { if (this.find_if(fnEQ)==null) { return this.push(e); } return null; };
Array.prototype.remove = function(e) { var a = this.findmul(e); for (var i=a.length-1; i>=0; --i) { this.splice(a[i], 1); } return a.length; };
Array.prototype.remove_if = function(fnEQ) { var a = this.findmul_if(fnEQ); for (var i=a.length-1; i>=0; --i) { this.splice(a[i], 1); } return a.length; };
Array.prototype.transform = function(fnOp) { for (var i=0; i<this.length; ++i) { this[i] = fnOp(this[i]); } return this; };

String.prototype.contains = function(str) { return (this.indexOf(str) > -1);};
String.prototype.trim = function (s) {if(s) return this.trimEnd(s).trimStart(s);else return this.replace( /(^[ \t\n\r]*)|([ \t\n\r]*$)/g, '' ) ;};
String.prototype.trimEnd=function(s){ if(this.endsWith(s)) { return this.substring(0,this.length-s.length);} return this;};
String.prototype.trimStart=function(s){ if(this.startsWith(s)){ return this.slice(s.length);}return this;};
String.prototype.startsWith = function(str) { return (this.indexOf(str) == 0); };
String.prototype.endsWith = function(str) { return (str.length <= this.length && this.substr(this.length - str.length, str.length) == str); };
String.prototype.len= function() { return this.replace(/[^\x00-\xff]/g, "rr" ).length; }     
String.prototype.replaceAll = function(search, replace){var regex = new RegExp(search, "g");return this.replace(regex, replace);};
String.prototype.escape = function() { return escape(this); };
String.prototype.unescape = function() { return unescape(this); };
String.prototype.isDate = function(){var reg=/^(\d{4})(-)(\d{1,2})(-)(\d{1,2}) (\d{1,2})(:)(\d{1,2})(:)(\d{1,2})$/; return this.match(reg)!=null;};
String.format=function(){var str=arguments[0];for(var i=1;i<arguments.length;i++){var reg=new RegExp("\\{"+(i-1)+"\\}","ig"); str=str.replace(reg,arguments[i]);}return str; };
String.isNullOrEmpty = function(str){ return str;};
Date.prototype.transform = function(format) { if(typeof(format) != 'undefined' && format == 'y-m-d') { return this.getFullYear() + '-' + (this.getMonth()+1) + '-' + this.getDate(); } else { return this.getFullYear() + '-' + (this.getMonth()+1) + '-' + this.getDate() + ' ' + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();}};

/**
 * 浏览器信息
 */
var max = {
	browser : {
		isIE     : navigator.userAgent.toLowerCase().contains('msie'),
		isIE5    : navigator.userAgent.toLowerCase().contains('msie 5'),
		isIE6    : navigator.userAgent.toLowerCase().contains('msie 6'),
		isIE7    : navigator.userAgent.toLowerCase().contains('msie 7'),
		isIE8    : navigator.userAgent.toLowerCase().contains('msie 8'),
		isIE9    : navigator.userAgent.toLowerCase().contains('msie 9'),
		is360    : navigator.userAgent.toLowerCase().contains('360se'),
		isWorld  : navigator.userAgent.toLowerCase().contains('theworld'),
		isGecko  : navigator.userAgent.toLowerCase().contains('gecko'),
		isSafari : navigator.userAgent.toLowerCase().contains('safari'),
		isOpera  : navigator.userAgent.toLowerCase().contains('opera'),
		isChrome : navigator.userAgent.toLowerCase().contains('chrome'),
		isFirefox: navigator.userAgent.toLowerCase().contains('firefox')
	},
	global : {
		getLoadingCode	: function() { return '<div class="icon_loading_1"></div>';},
		getClientWidth  : function() { return ((document.documentElement && document.documentElement.clientWidth) || document.body.clientWidth); },
		getClientHeight : function() { return ((document.documentElement && document.documentElement.clientHeight) || document.body.clientHeight); },
		getScrollTop    : function() { return ((document.documentElement && document.documentElement.scrollTop) || document.body.scrollTop); },
		getScrollLeft   : function(){ return ((document.documentElement && document.documentElement.scrollLeft) || document.body.scrollLeft); },
		getFullHeight   : function(){ if(document.documentElement.clientHeight>document.documentElement.scrollHeight) return document.documentElement.clientHeight;else return document.documentElement.scrollHeight;},
		getFullWidth    : function(){ return document.documentElement.scrollWidth;},
		getBrowserRect  : function(){ var r=new Object(); r.left = this.getScrollLeft();  r.top = this.getScrollTop(); r.width = this.getClientWidth(); r.height = this.getClientHeight(); r.bottom = r.top + r.height; r.right = r.left + r.width; return r; }      
	},
	
	coor : {
	   left    : function(e,left){if(typeof(left)=="number"){e.style.position="absolute";e.style.left=left+"px";}else{var offset=e.offsetLeft; if(e.offsetParent!=null) offset+=this.left(e.offsetParent); return offset; }},
	   top     : function(e,top){if(typeof(top)=="number"){e.style.position="absolute";e.style.top=top+"px";}else{var offset=e.offsetTop; if(e.offsetParent!=null) offset += this.top(e.offsetParent); return offset;}},
	   width   : function(e,w){ if(typeof(w)=="number"){e.style.width= w+"px";}else{return e.offsetWidth;}},
	   height  : function(e,h){   if(typeof(h)=="number"){e.style.height= h+"px";}else{return e.offsetHeight;}},
	   getRect : function(e){var r=new Object();r.left = this.left(e); r.top = this.top(e); r.width = this.width(e); r.height= this.height(e);r.bottom=r.top+r.height;r.right =r.left+r.width;return r;}
	}
};

// 跳转页面
function redirect(url) {
	url = typeof(url) != 'undefined' && url ? url : location.href;
	if(typeof(window.top.Redirect) != 'undefined') {
		window.top.Redirect(url);
	}else {
		location.href = url;
	}
}

/**
 * 检测是否有被选中的checkbox,注意：标识checkbox name固定为id
 */
function check() {
	var ids='';
	$("input[name='id[]']:checked").each(function(i, n){
		ids += $(n).val() + ',';
	});
	if(ids=='') {
		window.top.art.dialog({
			content: '请选择要操作的编号',
			icon: 'warning',
			lock: true,
			width: 200,
			height: 50,
			time:1.5
		});
		return false;
	}
	return true;
}

/**
 * 全选checkbox,注意：标识checkbox id固定为check_box
 * @param string name 列表check名称,如 uid[]
 */
function selectall(name) {
	var checked = $("#check_box").attr("checked");
	if(typeof(checked) == 'undefined' || !checked) {
		$("input[name='"+name+"']").attr('checked', false);
	} else {
		$("input[name='"+name+"']").attr('checked', true);
	}
}
//禁止选择一级分类
function check_cate(obj){ 
	if(parseInt($("option:selected",$(obj)).attr('pid'))==0){
		alert("一级分类禁止选择!");
		$('option[value="0"]',$(obj)).attr('selected','selected');
	}
}

/**
 * PARAM
 * @author lijia 2010-12-14
 * @param String	valPairs	"a=1&b=2"
 * @param String	elemSep		"&"
 * @param String	pairSep		"="
 * @return String
 *
 * @demo var _Prm = new PARAM("a=1&b=2", "&", "="); var a = _Prm["a"]; var b = _Prm["b"]
 */
function PARAM(valPairs, elemSep, pairSep)
{
	if (valPairs) {
		var aElem = valPairs.toString().split(elemSep);
		for (var i = 0; i < aElem.length; ++i) {
			var aPair = aElem[i].split(pairSep);
			(aPair.length>1) && (this[aPair[0]] = unescape(aPair[1]));
		}
	}
};


/**
 * getParam
 * @author lijia 2010-12-14
 * @param String	valPairs	"a=1&b=2"
 * @param String	sName		"a"
 * @param String	elemSep		"&"
 * @param String	pairSep		"="
 * @return String
 *
 * @demo var a = getParam("a=1&b=2", "a", "&", "=");
 */
function getParam(valPairs, sName, elemSep, pairSep)
{
	var xParam = new PARAM(valPairs, elemSep, pairSep);
	return xParam[sName] ? xParam[sName] : "";
};

/**
 * getVisitorName
 * @author lijia 2011-1-8
 * @param  Integer	visitor_id
 * @param  Integer	type
 * @return String
 */
function getVisitorName(visitor_id, type) {
	var name = '访客';
	if(type == 1) {
		//name = 'v';
		name = '';
	}
	return name + visitor_id;
};


/**
 * getVisitorFaceCls
 * @author lijia 2010-12-17
 * @param  Integer	visitor_id
 * @param  Integer	type(0: mini_face, 1: large_face, 2: bgcolor)
 * @return String
 */
function getVisitorFaceCls(visitor_id, type) {
	if(type == 1) {
		cls = 'icon_face_large face_large_';
	}else if(type == 2) {
		cls = 'face_';
	}else {
		cls = 'icon_face face_';
	}
	cls += visitor_id % 10;

	return cls;
};


/**
 * setParam
 * @author lijia 2010-12-14
 * @param String	valPairs	"a=1"
 * @param String	n	"b"
 * @param String	v	"2"
 * @return String
 *
 * @demo var sParam = setParam("a=1", "b", "2"); alert(sParam); // "a=1&b=2"
 */
function setParam(valPairs, n, v)
{
	valPairs = valPairs.toString();
	n = n.toString();
	//v = v.toString().escUrl();
	v = v.toString();
	var r = new RegExp("(^|\\W)"+n+"=[^&]*", "g");
	return (valPairs.match(r)) ? valPairs.replace(r, "$1"+n+"="+v) : valPairs+(valPairs ? "&" : "")+n+"="+v;
};

// var sParam = getURLParam("a", "http://w.w.w/?a=1&b=2);
function getURLParam(sName, sUrl)
{
	(!sUrl) && (sUrl = window.location.href);
	sUrl = sUrl.toString();
	var nIndex = sUrl.indexOf("?");
	return (nIndex>=0) ? getParam(sUrl.substr(nIndex+1), sName, "&", "=") : "";
};

// var sUrl = setURLParam("http://w.w.w/?a=1", "b", "2");
function setURLParam(u, n, v)
{
	u = u.toString();
	n = n.toString();
	//v = v.toString().escUrl();
	v = v.toString();
	var r = new RegExp("(^|\\W)"+n+"=[^&]*", "g");

	return (u.match(r)) ? u.replace(r, "$1"+n+"="+v) : u+(u.indexOf("?")==-1 ? "?" : "&")+n+"="+v;
};

/**
 * setCookie 设置cookie
 * @author lijia 2010-12-14
 * @param String	sName	"site_name"
 * @param String	sValue	"tipcoo"
 * @param Integer	nExpireSec	"3600"
 * @param String	sDomain	"tipcoo.com"
 * @param String	sPath	"/news/"
 * @return Boolean
 */
function setCookie(sName, sValue, nExpireSec, sDomain, sPath)
{
	var sCookie = sName + "=" + escape(sValue) + ";";

	//如果添加此cookie会导致cookie超长，则不添加。
	if((document.cookie.length + sCookie.length) >= 4096) {
		return false;
	}

	if (nExpireSec) {
		var oDate = new Date();
		oDate.setTime(oDate.getTime() + parseInt(nExpireSec) * 1000);
		sCookie += "expires=" + oDate.toUTCString() + ";";
	}
	if (sDomain) {
		sCookie += "domain=" + sDomain+";";
	}
	if (sPath) {
		sCookie += "path=" + sPath + ";"
	}
	document.cookie = sCookie;
	return true;
};


/**
 * getCookie 读取cookie
 * @author lijia 2010-12-14
 * @param String	sName	"site_name"
 * @return String
 */
function getCookie(sName){	
	return unescape(getParam(document.cookie, sName, "; ", "="));
};

/**
 * addClass
 * @author lijia 2010-12-14
 * @param String	obj			"#id|.class|type"
 * @param String	className	"current"
 * @return
 */
function addClass(obj, className) {
	$(obj).addClass(className);
};


/**
 * removeClass
 * @author lijia 2010-12-14
 * @param String	obj			"#id|.class|type"
 * @param String	className	"current"
 * @return
 */
function removeClass(obj, className) {
	$(obj).removeClass(className);
};


/**
 * alert_msg 信息提示
 * @author lijia	2012-12-14
 * @param string	msg
 * @param string	type
 * @return
 */
function alert_msg(msg, type) {
	if(type == 'top' && typeof(window.top.sysMessage) != 'undefined') {
		window.top.sysMessage(msg);
	}else {
		alert(msg);
	}

}

/**
 * confirm_url
 * @author lijia 2011-1-20
 * @param string	msg
 * @param string	type
 * @return
 */
function confirm_url(msg, trueUrl, falseUrl) {
	if(confirm(msg)) {
		location.href = trueUrl;
	}else if(typeof(falseUrl) != 'undefined') {
		location.href = falseUrl;
	}
}

/**
 * xss_clean 攻击过滤
 * @author lijia 2011-2-26
 * @param string	str
 * @param integer	level
 * @return
 */
function xss_clean(str, level){
	var s = str;
	s = s.replace(/</ig, '&lt;');
	s = s.replace(/>/ig, '&gt;');
	s = s.replace(/<script.*?>.*?<\/script>/ig, '');
	return s;
}

/**
 * make_link 字符串中截取并生成超链接
 * @author lijia 2011-3-12
 * @param string	str
 * @param boolean   tipbox
 * @param integer   limit
 * @return
 */
function make_link(str, tipbox, limit) {
	var reg = /(http:\/\/)?[A-Za-z0-9]+\.[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^ <>\"\"\u4e00-\u9fa5])*/gi;  
	var array1;  
	var array2 = [];
	var obstr = str;  
	var subos = '';
	var link;
	while(array1 = reg.exec(str)) {  
		array2.push(array1[0]);  
		obstr = obstr.replace(array1[0], '######');
	}  
	for(var i = 0; i < array2.length; i++) {
		link = array2[i].toLowerCase().indexOf('http://') > -1 ? array2[i] : 'http://' + array2[i];
		if(typeof(tipbox) != 'undefined' && tipbox == true) {
			subos = '<a href="' + link + '" target="_blank" tip=" " ' + (typeof(limit) != 'undefined' ? ' limit="' + limit + '"' : '') + '>' + array2[i] + '</a>';  
		}else {
			subos = '<a href="' + link + '" target="_blank">' + array2[i] + '</a>';  
		}
		obstr = obstr.replace('######', subos);  
	}  
	return obstr;  
}

function ubb2html(sUBB)
{
	var staticurl = "/";
	var emoturl = "/images/emoji/default/";
	var i,sHtml=String(sUBB),arrcode=new Array(),cnum=0;

	//sHtml=sHtml.replace(/&/ig, '&amp;');
	//sHtml=sHtml.replace(/[<>]/g,function(c){return {'<':'&lt;','>':'&gt;'}[c];});
	//sHtml=sHtml.replace(/\r?\n/g,"<br />");
	
	sHtml=sHtml.replace(/\[code\s*(?:=\s*([^\]]+?))?\]([\s\S]*?)\[\/code\]/ig,function(all,t,c){//code特殊处理
		cnum++;arrcode[cnum]=all;
		return "[\tubbcodeplace_"+cnum+"\t]";
	});

	sHtml=sHtml.replace(/\[(\/?)(b|u|i|s|sup|sub)\]/ig,'<$1$2>');
	sHtml=sHtml.replace(/\[color\s*=\s*([^\]"]+?)(?:"[^\]]*?)?\s*\]/ig,'<font color="$1">');
	sHtml=sHtml.replace(/\[size\s*=\s*(\d+?)\s*\]/ig,'<font size="$1">');
	sHtml=sHtml.replace(/\[font\s*=\s*([^\]"]+?)(?:"[^\]]*?)?\s*\]/ig,'<font face="$1">');
	sHtml=sHtml.replace(/\[\/(color|size|font)\]/ig,'</font>');
	sHtml=sHtml.replace(/\[back\s*=\s*([^\]"]+?)(?:"[^\]]*?)?\s*\]/ig,'<span style="background-color:$1;">');
	sHtml=sHtml.replace(/\[\/back\]/ig,'</span>');
	for(i=0;i<3;i++)sHtml=sHtml.replace(/\[align\s*=\s*([^\]"]+?)(?:"[^\]]*?)?\s*\](((?!\[align(?:\s+[^\]]+)?\])[\s\S])*?)\[\/align\]/ig,'<p align="$1">$2</p>');
	// sHtml=sHtml.replace(/\[img\]\s*(((?!")[\s\S])+?)(?:"[\s\S]*?)?\s*\[\/img\]/ig,'<img src="$1" />');
	sHtml=sHtml.replace(/\[img\]\s*(((?!")[\s\S])+?)(?:"[\s\S]*?)?\s*\[\/img\]/ig, function(all, url) {
		var str = '';
		if(url.indexOf('http://') == 0 || url.indexOf('HTTP://') == 0) {
			str = '<img src="' + url + '" />';
		}else if(url.indexOf('/') == 0 || url.indexOf('\\') == 0) {
			str = '<img src="' + url + '" />';
		}else {
			str = '<img src="' + (staticurl + url) + '" />';
		}
		return str;
	});
	sHtml=sHtml.replace(/\[img\s*=(?:\s*(\d*%?)\s*,\s*(\d*%?)\s*)?(?:,?\s*(\w+))?\s*\]\s*(((?!")[\s\S])+?)(?:"[\s\S]*)?\s*\[\/img\]/ig,function(all,p1,p2,p3,src){
		var str='<img src="'+src+'"',a=p3?p3:(isNaN(p1)?p1:'');
		if(!isNaN(p1))str+=' width="'+p1+'"';
		if(!isNaN(p2))str+=' height="'+p2+'"'
		if(a)str+=' align="'+a+'"';
		str+=' />';
		return str;
	});
	sHtml=sHtml.replace(/\[emot\s*=\s*([^\]"]+?)(?:"[^\]]*?)?\s*\/\]/ig,'<img emot="$1" src="'+emoturl+'$1.png" border="0" alt="$1" />');
	sHtml=sHtml.replace(/\[url\s*=\s*([^\]"]+?)(?:"[^\]]*?)?\s*\]/ig,'<a href="$1" target="_blank">');
	sHtml=sHtml.replace(/\[\/url\]/ig,'</a>');
	
	for(i=1;i<=cnum;i++)sHtml=sHtml.replace("[\tubbcodeplace_"+i+"\t]", arrcode[i]);

	sHtml=sHtml.replace(/(^|<\/?\w+(?:\s+[^>]*?)?>)([^<$]+)/ig, function(all,tag,text){
		return tag+text.replace(/[\t ]/g,function(c){return {'\t':'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',' ':'&nbsp;'}[c];});
	});
	
	return sHtml;
}

function html2ubb(sHtml)
{
	var mapSize={'xx-small':9,'9px':9,'x-small':11,'11px':11,'small':13,'13px':13,'medium':15,'15px':15,'large':19,'19px':19,'x-large':23,'23px':23,'xx-large':29,'29px':29};
	var regSrc=/\s+src\s*=\s*(["']?)\s*(.+?)\s*\1(\s|$)/i,regWidth=/\s+width\s*=\s*(["']?)\s*(\d+(?:\.\d+)?%?)\s*\1(\s|$)/i,regHeight=/\s+height\s*=\s*(["']?)\s*(\d+(?:\.\d+)?%?)\s*\1(\s|$)/i,regBg=/(?:background|background-color|bgcolor)\s*[:=]\s*(["']?)\s*((rgb\s*\(\s*\d{1,3}%?,\s*\d{1,3}%?\s*,\s*\d{1,3}%?\s*\))|(#[0-9a-f]{3,6})|([a-z]{1,20}))\s*\1/i
	var i,sUBB=String(sHtml),arrcode=new Array(),cnum=0;

	sUBB=sUBB.replace(/\s*\r?\n\s*/g,'');
	
	sUBB = sUBB.replace(/<(script|style)(\s+[^>]*?)?>[\s\S]*?<\/\1>/ig, '');
	sUBB = sUBB.replace(/<!--[\s\S]*?-->/ig,'');

	sUBB=sUBB.replace(/<br\s*?\/?>/ig,"\r\n");
	
	sUBB=sUBB.replace(/\[code\s*(=\s*([^\]]+?))?\]([\s\S]*?)\[\/code\]/ig,function(all,t,c){//code特殊处理
		cnum++;arrcode[cnum]=all;
		return "[\tubbcodeplace_"+cnum+"\t]";
	});
	
	sUBB=sUBB.replace(/<(\/?)(b|u|i|s)(\s+[^>]*?)?>/ig,'[$1$2]');
	sUBB=sUBB.replace(/<(\/?)strong(\s+[^>]*?)?>/ig,'[$1b]');
	sUBB=sUBB.replace(/<(\/?)em(\s+[^>]*?)?>/ig,'[$1i]');
	sUBB=sUBB.replace(/<(\/?)(strike|del)(\s+[^>]*?)?>/ig,'[$1s]');
	sUBB=sUBB.replace(/<(\/?)(sup|sub)(\s+[^>]*?)?>/ig,'[$1$2]');
	for(i=0;i<3;i++)sUBB=sUBB.replace(/<(span)(?:\s+[^>]*?)?\s+style\s*=\s*"((?:[^"]*?;)*\s*(?:font-family|font-size|color|background|background-color)\s*:[^"]*)"(?: [^>]+)?>(((?!<\1(\s+[^>]*?)?>)[\s\S]|<\1(\s+[^>]*?)?>((?!<\1(\s+[^>]*?)?>)[\s\S]|<\1(\s+[^>]*?)?>((?!<\1(\s+[^>]*?)?>)[\s\S])*?<\/\1>)*?<\/\1>)*?)<\/\1>/ig,function(all,tag,style,content){
		var face=style.match(/(?:^|;)\s*font-family\s*:\s*([^;]+)/i),size=style.match(/(?:^|;)\s*font-size\s*:\s*([^;]+)/i),color=style.match(/(?:^|;)\s*color\s*:\s*([^;]+)/i),back=style.match(/(?:^|;)\s*(?:background|background-color)\s*:\s*([^;]+)/i),str=content;
		if(face)str='[font='+face[1]+']'+str+'[/font]';
		if(size)
		{
			size=mapSize[size[1].toLowerCase()];
			if(size)str='[size='+size+']'+str+'[/size]';
		}
		if(color)str='[color='+formatColor(color[1])+']'+str+'[/color]';
		if(back)str='[back='+formatColor(back[1])+']'+str+'[/back]';
		return str;
	});
	function formatColor(c)
	{
		var matchs;
		if(matchs=c.match(/\s*rgb\s*\(\s*(\d+)\s*,\s*(\d+)\s*,\s*(\d+)\s*\)/i)){c='#';for(var i=1;i<=3;i++)c+=(matchs[i]-0).toString(16);}
		c=c.replace(/^#([0-9a-f])([0-9a-f])([0-9a-f])$/i,'#$1$1$2$2$3$3');
		return c;
	}
	sUBB=sUBB.replace(/<img(\s+[^>]*?)\/?>/ig,function(all,attr){
		var emot=attr.match(/\s+emot\s*=\s*(["']?)\s*(.+?)\s*\1(\s|$)/i);
		if(emot)return '[emot='+emot[2]+'/]';
		var url=attr.match(regSrc),w=attr.match(regWidth),h=attr.match(regHeight),a=attr.match(/\s+align\s*=\s*(["']?)\s*(\w+)\s*\1(\s|$)/i),str='[img',p='';
		if(!url)return '';
		if(w||h)p+=(w?w[2]:'')+','+(h?h[2]:'');
		if(a)p+=(w||h?',':'')+a[2];
		if(p)str+='='+p;
		str+=']'+url[2];
		return str+'[/img]';
	});

	sUBB=sUBB.replace(/\<a\s*([^\]]+?)?\>([\s\S]*?)\<\/a\>/ig,function(all, u, t){ //url特殊处理
		var url = u.match(/^href\s*=\s*"([^"]+)/i);
		if(url) {
			return "[url=" + url[1] + "]" + t + "[/url]";
		}else {
			return "[url=#]" + t + "[/url]";
		}
	});

	sUBB=sUBB.replace(/((\s|&nbsp;)*\r?\n){3,}/g,"\r\n\r\n");//限制最多2次换行
	sUBB=sUBB.replace(/^((\s|&nbsp;)*\r?\n)+/g,'');//清除开头换行
	sUBB=sUBB.replace(/((\s|&nbsp;)*\r?\n)+$/g,'');//清除结尾换行
	
	for(i=1;i<=cnum;i++)sUBB=sUBB.replace("[\tubbcodeplace_"+i+"\t]", arrcode[i]);

	sUBB=sUBB.replace(/<[^<>]+?>/g,'');//删除所有HTML标签
	sUBB=sUBB.replace(/&lt;/ig, '<');
	sUBB=sUBB.replace(/&gt;/ig, '>');
	sUBB=sUBB.replace(/&nbsp;/ig, ' ');
	sUBB=sUBB.replace(/&amp;/ig, '&');
	
	return sUBB;
}