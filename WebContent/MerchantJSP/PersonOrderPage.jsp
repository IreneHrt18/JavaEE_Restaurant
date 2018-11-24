<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>订单信息</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	 crossorigin="anonymous">
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<script type="text/javascript">	
		
		//获得需要添加的html代码
		function getListText(item) {
			var href="../OrderServlet?action=statement&ordernumber=";
			var text = "<tr>" +
				"<td scope='row'><a href="+href + item.ORDERNO + ">"+item.ORDERNO +"</a></td>" +
				"<td>" + item.USERNO + "</td>" +
				"<td>" + item.PRICE + "</td>" +
				"<td>" + item.TIME + "</td>" +
				"<td>" + item.ORDERSTATE +"</td>" +				
				"<td>" + item.COMMENTSTATE +"</td>" +		
				"</tr>";
			return text;
		}
		//初始化页面
		$(document).ready(function(){
			var url = "../OrderServlet?action=loadPersonalOrder";
			var listText = "";
			$.getJSON(url, function (data, textstatus, jqxhr) {
				if(data !=null){
					$.each(data, function (index, item) {
							listText += getListText(item);
						})
						$("#dishlist").html(listText);
						$("[type=search]").val("");	
				}
			})
		})
		
		//绑定事件
		$(function () {
			$(document).on("click", "[type=button]", function () {
				var url = "../OrderServlet?action=search&searchText=" + $("[type=search]").val();
				var listText = "";
				$.getJSON(url, function (data, textstatus, jqxhr) {
					if (data != null) {
						$.each(data, function (index, item) {
							listText += getListText(item);
						})
						$("#dishlist").html(listText);
						$("[type=search]").val("");
					}
				})
			})
		})

	</script>

</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="#">订单信息</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">菜品信息</a>
				</li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search" placeholder="订单编号" aria-label="Search">
				<input type="button" class="btn btn-outline-success my-2 my-sm-0" id="searchButton" value="搜索">
			</form>
		</div>
	</nav>

<div class="alert alert-info" role="alert">
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
	</table></div>
</body>

</html>