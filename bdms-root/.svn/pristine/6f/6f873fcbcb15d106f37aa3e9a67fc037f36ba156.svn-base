
var tables = null;
var length = 0 ;
var size = 5;
	
var  searchTables = null ;
var  searchlength = 0 ;

jQuery(function(){
	
	
	
	var dialog = jQuery('#bb');
	
	jQuery("#btn").click(function(){
		dialog.dialog();
	});
	
	var db_type = null;
	var db_ip   = null;
	var db_name = null;
	var db_port = null;
	var db_user = null;
	var db_password = null;
	
	//数据库连接测试按钮监听
	jQuery("#test_db_conn").click(function(){
		
		var butt = jQuery(this);
		butt.html("<img alt='拼命测试中...' src='style/themes/icons/loading.gif'>");
		
		var flag = true;
		//检查参数设置
		jQuery("#db_info input").each(function(){
			
			var t = jQuery(this);
			if (!t.validatebox('isValid')) {
			        flag = false;
			        t.focus();
			        return false;
			    }else{
			    	var name = t.attr("name");
			    	switch(name){
			    	   case '1' : db_ip = t.val();break;
			    	   case '2' : db_name = t.val();break;
			    	   case '3' : db_port = t.val();break;
			    	   case '4' : db_user = t.val();break;
			    	   case '5' : db_password = t.val();break;
			    	}
			    }
			
		});
		
		if(flag){
			//提交测试请求
			db_type = jQuery("#db_select input:checked").val();
			jQuery.post("sqoop/testDBConn",{type:db_type,host:db_ip ,name:db_name ,port:db_port ,user:db_user,password:db_password } ,function(info){
				
				var status = info.status;
				butt.attr("name",status);
				
				if( status == 'ok'){
					butt.linkbutton({
						  iconCls:'icon-ok',
						  text:'测试通过'
					});
					
					tables = info.tables;
					length = tables.length;
					showPages(length);
					putTables(false,1,size);
				}else{
					butt.linkbutton({
						  iconCls:'icon-no',
						  text:'测试未通过'
					});
				}
				
			},'json');
		}else{
			
			butt.linkbutton({
				  iconCls:'icon-no',
				  text:'测试未通过'
			});
		}
		
		//ajaxPost(url,data,"");
	});
	
	
	jQuery("#all_c").click(function(){
		jQuery(".ccb").attr("checked",this.checked);
		
	});
	
});

//生成分页条
function showPages(n){
	
	jQuery("#paging").pagination({
		
		displayMsg:'',
		total:n,
		pageSize: size,
		layout:['sep','first','prev','links','next','last','sep','manual'],
		beforePageText:'跳转到',
		afterPageText:'页',
		onSelectPage:function(pageNumber, pageSize){
				
			jQuery(this).pagination('loading');
			if(searchTables != null ){
				putTables(true,pageNumber,pageSize);
			}else{
				putTables(false,pageNumber,pageSize);
			}
			
			jQuery(this).pagination('loaded');
		}

	});
}
//插入表数据
function putTables(isSearch,pageNumber,pageSize){
	
	var len = 0;
	var data = null;
	if(isSearch){
		len = searchlength;
		data = searchTables
	}else{
		len = length;
		data = tables;
	}
	
	var tbody = jQuery("#table_info tbody").empty();
	 
	var start = pageSize * ( pageNumber - 1 );
	var end = pageSize * pageNumber;
	
	if(start < 0 ){
		start = 0;
	}
	
	if(end > len){
		end = len;
	}
	
	var table = null;
	var name = null;
	var type = null;
	
	var  tr  = null;
	var  td1 = null;
	var  td2 = null;
	var  td3 = null;
	var  label1 = null;
	var  label2 = null;
	var  input = null;
	for(var i = start ; i < end ; i++ ){
		 
		 table = data[i];
		 name = decodeURI(table.name);
		 type = table.type;
		 
		 tr = jQuery("<tr></tr>");
		 td1 = jQuery("<td class='tableName'></td>");
		 td2 = jQuery("<td></td>");
		 td3 = jQuery("<td></td>");
		 
		 td1.attr("title",name);
		 label1 = jQuery("<div></div>");
		 label1.attr("for",name).text(name);
		 label2 = jQuery("<label></label>");
		 label2.attr("for",name).text(type);
		 
		 input = jQuery("<input name='table' type='radio'></input>");
		 input.attr("value",name);
		 
		 td1.append(label1);
		 td2.append(label2);
		 td3.append(input);
		 
		 tr.append(td1).append(td2).append(td3);
		 tbody.append(tr);
	}
	
}

//插入字段数据
function putColumns(columns){
	
	var tbody = jQuery("#column_info tbody").empty();
	
	var table = null;
	var name = null;
	var type = null;
	
	var  tr  = null;
	var  td1 = null;
	var  td2 = null;
	var  td3 = null;
	var  label1 = null;
	var  label2 = null;
	var  input = null;
	
	var len = columns.length;
	
	jQuery.each(columns,function(i){
		
		column = columns[i];
		name = decodeURI(column.name);
		type = column.type;
		
		tr = jQuery("<tr></tr>");
		td1 = jQuery("<td class='columnName'></td>");
		td2 = jQuery("<td></td>");
		td3 = jQuery("<td></td>");
		
		td1.attr("title",name);
		label1 = jQuery("<div></div>");
		label1.attr("for",name).text(name);
		label2 = jQuery("<label></label>");
		label2.attr("for",name).text(type);
		
		input = jQuery("<input class='ccb' name='table' type='checkbox'></input>");
		
		input.attr("value",name);
		
		input.click(function(){
			
			if(jQuery(".ccb:checked").length >= len ){
				
				jQuery("#all_c").attr("checked",true);
			}else{
				jQuery("#all_c").attr("checked",false);
			}
			
		});
		
		td1.append(label1);
		td2.append(label2);
		td3.append(input);
		
		tr.append(td1).append(td2).append(td3);
		tbody.append(tr);
		
	});
	
}

//检索
function doSearch(value){
	
	searchlength = 0;
	
	if( value.trim() == "" ){
		
		searchTables = null;
		showPages(length);
		putTables(false,1,size);
		
	}else{
	
		searchTables = new Array();
		
		
		var name = null;
		var table = null;
		jQuery.each(tables,function(i){
			
			table = tables[i];
			name = decodeURI(table.name);;
			if(name.indexOf( value ) > 0  ){
				searchTables[searchlength] = table;
				searchlength = searchlength + 1 ;
			}
		});
		
		showPages(searchlength);
		putTables(true,1,size);
	}
	
}


function nextStep(n) {
	
	switch(n){
	
		case 2 : jQuery('#bb').dialog("close"); jQuery('#step2').dialog();break;
		case 3 : 
			
			var butt = jQuery("#test_db_conn");
			var name = butt.attr("name");
			if(name == 'ok' ){
				jQuery('#step2').dialog("close"); 
				jQuery('#step3').dialog();
				
			}else{
				jQuery.messager.alert('错误','测试未通过,不能执行下一步!','error');
			}
			
			break;
		case 4 : 
			
			var input = jQuery("#table_info tbody input:checked");
			if(input.length < 1 ){
				jQuery.messager.alert('错误','未选择表,不能执行下一步!','error');
				
			}else{
				
				var tableName = input.val();
				
				var status = null;
				
				jQuery.post("sqoop/getColumn",{table:tableName},function(info){
					
					status = info.status;
					if( status == "ok"){
						putColumns(info.column);
						jQuery('#step3').dialog("close"); 
						jQuery('#step4').dialog();
						
					}else{
						jQuery.messager.alert('错误','获取' + tableName +' 下的字段失败,不能执行下一步!','error');
					}
					
				},"json");
				
			}
			
			break;
			
		case 5 :
			
			var columns =  jQuery(".ccb:checked");
			var scs = "";
			
			if(columns.length < 1 ){
				jQuery.messager.alert('错误','没有选择导入数据的字段,不能执行下一步!','error');
			}else{
				
				
				jQuery.each(columns,function(i){
					scs = scs + jQuery(columns[i]).val() + ";";
				});
				
				
				jQuery.post("sqoop/doimport",{selectColumns:scs},function(info){
					
					
					
				},"json");
			}
			
			
			
			break;
	}
} 

function back(n) {
	switch(n){
		case 1 : jQuery('#step2').dialog("close"); jQuery('#bb').dialog();break;
		case 2 : jQuery('#step3').dialog("close"); jQuery('#step2').dialog();break;
		case 3 : jQuery('#step4').dialog("close"); jQuery('#step3').dialog();break;
	}
} 


//easyui 扩展方法
jQuery(function(){
	
	var wordPatt = new RegExp(/\w+/);
	var numberPatt = new RegExp(/\d+/);
	var ipPatt = new RegExp(/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/);
	
	jQuery.extend(jQuery.fn.validatebox.defaults.rules, { 
		
	    word: {    
	        validator: function(value){ 
	        	
	            return wordPatt.test(value);  
	        },    
	        message: '输入内容无效!'   
	    },   
		number: {    
			validator: function(value){
				
				var length = value.length;
				
				return numberPatt.test(value) && length < 6;  
			},    
			message: '输入内容无效!'   
		},    
	    ip: {    
	    	validator: function(value){
	    		
	    		return ipPatt.test(value);  
	    	},    
	    	message: '输入内容无效!'   
	    }    
	});  

});

