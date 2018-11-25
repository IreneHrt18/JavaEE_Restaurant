<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Bean.User" %>
<%@ page import="java.net.URL" %>


<%
//User user=(User)session.getAttribute("user");
application.setAttribute("userno",1004);
User user = new User();
user.setUSERNO("1004");
user.setUSERNAME("刘陈俊");
user.setPASSWORD("1234");
user.setPHONENUM("18874599632");
user.setICONURL("/Image/head_default.jpg");
user.setCUSNOTE("善良");
session.setAttribute("user", user);	
String imgurlhead = user.getICONURL();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>个人中心</title>

<!-- meta -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- css -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../Css/PersonalCenter.css">
<!-- js -->
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script type="text/javascript">

//获得需要添加的html代码
function getListText(item) {
	var href="../OrderServlet?action=statement&ordernumber=";
	if(item.ORDERSTATE=="未支付"){

		var text = "<tr>" +
		"<td scope='row'><a href="+href + item.ORDERNO + ">"+item.ORDERNO +"</a></td>" +
		"<td>" + item.USERNO + "</td>" +
		"<td>" + item.PRICE + "</td>" +
		"<td>" + item.TIME + "</td>" +
		"<td><a href = #>" + item.ORDERSTATE +"</a></td>" +				
		"<td>" + item.COMMENTSTATE +"</td>" +		
		"</tr>";
	}
	else{
		var text = "<tr>" +
		"<td scope='row'><a href="+href + item.ORDERNO + ">"+item.ORDERNO +"</a></td>" +
		"<td>" + item.USERNO + "</td>" +
		"<td>" + item.PRICE + "</td>" +
		"<td>" + item.TIME + "</td>" +
		"<td>" + item.ORDERSTATE +"</td>" +				
		"<td>" + item.COMMENTSTATE +"</td>" +		
		"</tr>";
	}
	
	return text;
}

//页面加载
$(document).ready(function(){
	var userno = ${userno};	
	var url = "../PersonalServlet?action=search&searchText=" + userno;
	$.getJSON(url, function (data, textstatus, jqxhr) {
		if(data!=null){
			var Text = "<br/><b>ID: " + data[0].USERNO + "</b><br/><b>用户名: " + data[0].USERNAME.toString() +
			"</b><br/><b>个性标签: " + data[0].CUSNOTE + "</b><br/>";
			var text = "<b>" + data[0].CUSNOTE + "</b><br/><b>" + data[0].USERNAME.toString() +"   "+
			data[0].PHONENUM + "</b><br/>";
			$("#upload-user").html(Text);			
			$("#address").append(text);
		}
	})
	var url2 = "../OrderServlet?action=load";
	var listText = "";
	$.getJSON(url2, function (data, textstatus, jqxhr) {
		if(data !=null){
			$.each(data, function (index, item) {
					listText += getListText(item);
				})
			$("#dishlist").html(listText);
				}
			})
})

//实现图片上传预览
	function setImagePreview() {
		var docObj = document.getElementById("upload-image");
		var imgObjPreview = document.getElementById("Img");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '190px';
			imgObjPreview.style.height = '180px';
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("add");
			//必须设置初始大小
			localImagId.style.width = "190px";
			localImagId.style.height = "180px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}
	
</script>
</head>
<body>
	<!-- 图片上传盒子 -->
	<br />
	<font color = gray><h2>个人中心</h2></font>
	
				
			
	
	<div class="upload-box" style="background: url(../Image/bk.jpg)">
		<section id="add" class="add">
			<img id="Img" alt="默认头像" src=<%=request.getContextPath()+user.getICONURL()%>>  
			<form action="../UploadImage"  enctype="multipart/form-data" method="post" >
			<input type="file" name="upload-image" id="upload-image"
				accept="image/jpg,image/png,image/bmp,image/jpeg" multiple="multiple"
				onchange="javascript:setImagePreview();">
			<br><br><br><br><br><br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value="开始上传" class="btn btn-primary">
			</form>
		</section>
		<div class="form-group upload-user" id="upload-user">
		<%--<label><%out.println("ID: " + request.getContextPath() + user.getICONURL()); %></label>	 --%>
	</div>
	</div>
	<div class="order-box">
	<a href = "<%=request.getContextPath()%>/CustomerJSP/PersonalCenter.jsp">待付款</a>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<a href = "<%=request.getContextPath()%>/CustomerJSP/PersonalCenter.jsp">待评价</a>
	</div>
	<div class="personalInfo" id="address">
	<label>送餐地址</label>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href = "<%=request.getContextPath()%>/CustomerJSP/PersonalCenter.jsp">编辑</a>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<a href = "<%=request.getContextPath()%>/CustomerJSP/PersonalCenter.jsp">删除</a>
	<br/>
	
	</div>
	<div class="AllOrder">
	<label>我的全部订单</label>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th scope="col">订单号</th>
				<th scope="col">用户编号</th>
				<th scope="col">消费金额</th>
				<th scope="col">订单时间</th>
				<th scope="col">订单状态</th>
				<th scope="col">是否评论</th>
			</tr>
		</thead>
		
		<tbody id="dishlist">

		</tbody>
	</table>
	</div>
	
</body>
</html>