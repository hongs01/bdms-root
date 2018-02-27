<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="DS,大数据，Hadoop, SQOOP" />
<meta name="description" content="迪爱斯大数据管理系统" />
<jsp:include page="sqoopcommonheader.jsp"></jsp:include>
<title>DSMS_迪爱斯大数据管理系统_sqoop数据导入</title>
</head>
<body>
	<div id="cc" class="easyui-layout" style="width:980px;height:640px;">  
	 
	 <!-- 
	    <div class="head" data-options="region:'north',collapsible:false,border:false" 
	    style="height:200px;background: none repeat scroll 0 0 #FAFAFA;">
	    	<div class="logo">
				<ul>
					<li id="mlogo"><a href="#" ><img src="style/images/bdms_logo.png"></a></li>
					<li id="tname"><div class="systitle" > DS数据导入系统   </div> </li>				
				</ul>
			</div>
	    </div>    -->
	    
	    <div class='main' data-options="region:'center',collapsible:false,border:false" 
	    style="padding:5px;background: none repeat scroll 0 0 #FAFAFA;">
	    	<div class="detail">
			   	<p>
			    	<label>系统说明：</label>
			    </p>
				<p>
					<label>本系统提供从关系型数据库到大数据平台的数据导入解决方案。</label>
					<label>支持sqlserver,oracle,mysql等流行的关系型数据库。</label>
					<label>可以将数据导入到大数据平台的HDFS中。</label>
				</p>
			</div>
			
			<div id="centent"  >
				<div id="begin">
					<a id="btn" href="#" class="easyui-linkbutton" data-options="plain:true,size:'large'" ><font size="5" color="#0000FF">开始导入数据</font></a>  
				</div>
			</div>
	    </div>   
	    
	    <div class="footer" data-options="region:'south',collapsible:false,border:false" style="height:100px;">
	    	
				@Copyright (C)2013-2014<a href="#">上海迪爱斯通信设备有限公司</a>
			
	    </div>   
	</div>  
	
	<div id="shows" style="display: none;">
	
		<div id="bb" title="step 1 数据库选择" style="width:200px;height:200px;"  
	        data-options="iconCls: 'icon-edit',modal:true,buttons:[{
					text:'下一步',
					handler:function(){ nextStep(2);}
				}]">   
	   			<table class = "db_list" id="db_select">
	   				<tr>
	   					<td><label for="sql" >SQLSERVER</label></td> 
	   					<td><input id="sql" name="db" type="radio" value="1" checked="checked" /></td>
	   				</tr>
	   				<tr>
	   					<td><label for="ora">ORACLE </label></td>    
	   					<td><input id="ora" name="db" type="radio" value="2" /></td>
	   				</tr>
	   				<tr>
	   					<td><label for="mys">MYSQL </label></td>     
	   					<td><input id="mys" name="db" type="radio" value="3" /></td>
	   				</tr>
	   			</table>
	     </div> 
	     
	     <div id="step2" title="step 2  测试数据库连接 " style="width:390px;height:290px;"  
	        data-options="iconCls: 'icon-edit',modal:true,buttons:[{
					text:'上一步',
					handler:function(){ back(1); }
				},{
					text:'下一步',
					handler:function(){nextStep(3);}
				}]">   
	   			<table class = "db_list" id="db_info">
	   				<tr>
	   					<td>数据库主机IP ：</td> 
	   					<td><input id="dbIP" name="1"  value="192.168.9.228" style="width: 171px;height: 22px;" class="easyui-validatebox textbox validatebox-text validatebox-invalid" data-options="required:true,validType:'ip',missingMessage:'*必填项'" /></td>
	   				</tr>
	   				<tr>
	   					<td>数据库名称 &nbsp;&nbsp; ：</td> 
	   					<td><input id="dbName" name="2" value="ias" style="width: 171px;height: 22px;" class="easyui-validatebox textbox validatebox-text validatebox-invalid" data-options="required:true,validType:'word',missingMessage:'*必填项'" /></td>
	   				</tr>
	   				<tr>
	   					<td>数据库端口 &nbsp;&nbsp; ： </td> 
	   					<td> <input id="dbPort" name="3" style="width: 171px;height: 22px;" class="easyui-validatebox textbox validatebox-text validatebox-invalid" data-options="validType:'number'" /></td>
	   				</tr>
	   				<tr>
	   					<td>用 &nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;名 &nbsp;&nbsp; ：  </td> 
	   					<td><input id="user" name="4" value="ias" style="width: 171px;height: 22px;" class="easyui-validatebox textbox validatebox-text validatebox-invalid" data-options="required:true,validType:'word',missingMessage:'*必填项'" /></td>
	   				</tr>
	   				<tr>
	   				 	<td>密  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码 &nbsp;&nbsp;  ：</td> 
	   				 	<td><input id="password" name="5" value="ias" type="password" style="width: 171px;height: 22px;" class="easyui-validatebox textbox validatebox-text validatebox-invalid" data-options="required:true,validType:'word',missingMessage:'*必填项'"/></td>
	   				 </tr>
	   				 
	   				<tr style="text-align: center;padding-top: 3px; " > 
	   					<td colspan="2"><button id="test_db_conn" name="no" class="easyui-linkbutton" >测试连接</button></td>
	   				</tr>
	   				
	   			</table>
	   			
	     </div> 
	     
	     <div id="step3" title="step 3   选择导入表 " style="width:500px;height:360px;"  
	        data-options="iconCls: 'icon-edit',modal:true,buttons:[{
					text:'上一步',
					handler:function(){ back(2); }
				},{
					text:'下一步',
					handler:function(){nextStep(4);}
				}]">   
	   			<table class = "db_list" id="table_info">
	   				<thead>
	   					<tr>
	   						<td colspan="3">
	   						<input id="ss" class="easyui-searchbox" style="width:95%;height: 25px;" 
							data-options="prompt:'输入检索字段名',searcher:doSearch"></input>
							<div style="height: 5px;"></div>
							</td>
	   					</tr>
	   					
	   					<tr>
	   						<th width="260px">表名</th>
	   						<th width="120px">类别</th>
	   						<th width="120px">选择</th>
	   					</tr>
	   				</thead>
	   				<tbody></tbody>
	   			</table>
	   			
	   			<div style="height: 12px;"></div>
	   			<div id="paging"></div>
	     </div> 
	     
	    
	     <div id="step4" title="step 4   选择导入字段 " style="width:500px;"  
	        data-options="iconCls: 'icon-edit',modal:true,buttons:[{
					text:'上一步',
					handler:function(){ back(3); }
				},{
					text:'完成',
					handler:function(){nextStep(5);}
				}]">   
	   			<table class = "db_list" id="column_info">
	   				<thead>
	   			
	   					<tr>
	   						<th width="260px">字段名</th>
	   						<th width="120px">类别</th>
	   						<th width="120px">选择&nbsp;<input type="checkbox" id="all_c" style="border: 1px solid; "></th>
	   					</tr>
	   				</thead>
	   				<tbody></tbody>
	   			</table>
	   			
	   			<div style="height: 5px;"></div>
	   			<div id="c_paging"></div>
	   			
	     </div> 
	      
  	</div>
  	
  	
</body>
</html>