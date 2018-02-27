var current=0;
var t=0;
function test2(dt){
	window.t=dt;
$.ajax({
url:"/bdms-web/memcached/h2?dt="+dt,
type:'POST',
dataType:'json ',
timeout:2000,
cache:false,
error:function(resultData){
	alert("xml");
},

success:function(resultData){
	var fristpagehtml;
	var prevpagehtml; 
	var nextpagehtml;
	var lastpagehtml;
	var gopagehtml;
	var length =resultData.length;
	var t=resultData[length-1].pagecount;
	gopagehtml="<input id='edit_count' size='4' type='text' /> <input type='button'  value='go' onclick='test3();'/>";
	
	fristpagehtml = "<a onclick='test2("+(0+1)+");' href='javascript:void(0);'>首页</a>";
	if((dt-0)<=1){
		prevpagehtml = "<a>上一页</a>";
	}else{
		prevpagehtml = "<a onclick='test2("+(dt-1)+");' href='javascript:void(0);'>上一页</a>";
	}
	if((dt-0)<(t-0)){
		nextpagehtml = "<a onclick='test2("+(dt-0+1)+");' href='javascript:void(0);'>下一页</a>";
	}else{
		nextpagehtml = "<a>下一页</a>";
	}
	
	lastpagehtml = "<a onclick='test2("+t+");' href='javascript:void(0);'>未页</a>";
	
		var html = "<table border=1px  style='font-size: 12px; color: red; text-align:center'><tr><td width='160'>ID</td><td width='200'>GIX_X</td><td width='160'>GIX_Y</td></tr>";
	   for(var i=0;i<resultData.length-1;i++){
		   
		   html = html+ "<tr ><td>"
		   +resultData[i].id+"</td><td>"
		   +resultData[i].GIX_X+"</td><td>"
		   +resultData[i].GIY_Y
		   +"</td></tr>";
	   }		
		html = html + "<tr ><td colspan=3 text-align=center>"+fristpagehtml+"&nbsp&nbsp"+prevpagehtml+"&nbsp&nbsp"+"共"+resultData[length-1].pagecount+"页&nbsp;当前第"+dt+"页"+"&nbsp&nbsp"+nextpagehtml+"&nbsp&nbsp"+lastpagehtml+"&nbsp&nbsp"+gopagehtml+"</td></tr>";
			html = html+"</table>";
			document.getElementById("div").innerHTML=html;   
       
}
})}

function test3(){
	var str=document.getElementById("edit_count").value;
	test2(str);
}
setInterval("test2(window.t)",1000*15);
 


