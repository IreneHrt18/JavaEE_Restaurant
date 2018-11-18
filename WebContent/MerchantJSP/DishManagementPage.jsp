<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>菜品信息</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	 crossorigin="anonymous">
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<script type="text/javascript">
		//初始化页面
		$(document).ready(function(){
			var url = "../DishServlet?action=showByPage&currentPage=1&pageSize=6";
			var listText ="";
			$.getJSON(url,function(data,textstatus,jqxhr){
				if(data!=null){
					$.each(data,function(index,item){
						listText += getListText(item);
					});
				}
				$("#dishlist").html(listText);
			});
			
		});
		//获得需要添加的html代码
		function getListText(item) {
			var text = "<tr>" +
				"<td scope='row'>" + item.DISHNO + "</td>" +
				"<td>" + item.DISHNAME + "</td>" +
				"<td>" + item.PRICE + "</td>" +
				"<td>" + item.DESCRIPTION + "</td>" +
				"<td><img src= " + item.IMG + " width='50px' height='50px'></td>" +
				"</tr>";
			return text;
		}
		//绑定事件
		$(function () {
			$(document).on("click","#searchButton", function () {
				var url = "../DishServlet?action=search&searchText=" + $("[type=search]").val();
				var listText = "";
				$.getJSON(url, function (data, textstatus, jqxhr) {
					if (data != null) {
						$.each(data, function (index, item) {
							listText += getListText(item);
						})
						$("#dishlist").html(listText);
					}else{
						$("#dishlist").html("未找到对应菜品");
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
				<li class="nav-item active"><a class="nav-link" href="homepage.html">订单信息<span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="navbar-brand" href="#">菜品信息</a>
				</li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search" placeholder="菜品编号" aria-label="Search">
				<input type="button" class="btn btn-outline-success my-2 my-sm-0" id="searchButton" value="搜索">
			</form>
		</div>
	</nav>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">菜品编号</th>
				<th scope="col">菜品名称</th>
				<th scope="col">菜品价格</th>
				<th scope="col">菜品描述</th>
				<th scope="col">菜品图片</th>
			</tr>
		</thead>
		<tbody id="dishlist">
				
		</tbody>
	</table>
</body>

</html>