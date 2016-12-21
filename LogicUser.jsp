<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function ajaxFileUpload(fileElementId,imgTargetId,imgHiddenId,saveDir){
		var arr_filetype = ["jpg","png","jpeg","JPG","PNG","JPEG"];//支持的图片类型
		 //判断上传文件类型是否是图片类型
		var filetype = $("#"+fileElementId).val().split(".")[1];  
		var num = arr_filetype.indexOf(filetype);//存在返回1，不存在返回-1
		if(num == "-1"){  
	        alert("请选择"+arr_filetype.join(",")+"类型的图片文件！");   
	        return false;  
	    	}
		$.ajaxFileUpload(
				 {  
		                url:'LogicUser_getPicefile.action?saveDir='+saveDir,//用于文件上传的服务器端请求地址  
		                secureuri:false,//一般设置为false  
		                fileElementId:fileElementId,//文件上传空间的id属性  <input type="file" id="file" name="file" />  
		                dataType: 'json',//返回值类型 一般设置为json  
		                success: function (data, status)  //服务器成功响应处理函数  
		                {  
		                	alert("------------");
		                    //alert(data.fileFileName);//从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量  
		                    $("#"+imgTargetId).attr("src","<%=path%>"+"/"+saveDir+"/"+data.fileFileName);  
		                    //将上传的图片路径（这里是相对服务器根路径的路径 ）暂存在该隐藏标签的value中，  
		                    $("#"+imgHiddenId).val("/"+saveDir+"/"+data.fileFileName);  
		                    if(typeof(data.error) != 'undefined')  
		                    {  
		                        if(data.error != ''){  
		                            alert(data.error);  
		                        }else{  
		                            alert(data.message);  
		                        }  
		                    }  
		                },  
		                error: function (data, status, e)//服务器响应失败处理函数  
		                {  
		                    alert(e+data+status);  
		                }  
		            }  	
		);
		}
</script>
</head>
<body>
<form  action="LogicUser_getLogicUser.action" method="post">
        <table border="1">
           <tr><Td colspan="3">USerLogin</Td></tr>
           <tr>
           <td> UserName：<input type="text" name="logicname"></td>
           <td rowspan="2"><img  id="pic_target" src=""></td>
           </tr>
           <tr><td> Password：<input type="password" name="password"></td></tr>      
           <tr>
           <Td colspan="1">
           <input type="submit" value="Login">
           <input type="reset" value="Reset">
           </Td>
           <td>
           <!--  
           <input type="file">
           <font color="red">支持jpg,png,jpeg,JPG,PNG,JPEG文件的上传</font>
           -->
           </td>
           </tr>
         
        </table>
    </form>
    <img  id="pic_target" src="">
    <input  type="text" id="pic_hidden" name="pic_hidden" style="display: none;"/>
    <input type="file" id="file_pic" name="file" onchange="return ajaxFileUpload(this.id,'pic_target','pic_hidden','heads');" style="width: 140px;"/>
	<script type="text/JavaScript" src="<%=basePath %>Scripts/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>Scripts/ajaxfileupload.js"></script>
</body>
</html>