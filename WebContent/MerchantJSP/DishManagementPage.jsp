<%@page import="Bean.PageModel"%>
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
	
	 <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js" type="text/javascript" charset="UTF-8"></script>
	<script src="./DishManagementPageJS.js" charset="UTF-8" type="text/javascript">	</script>
	
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	 crossorigin="anonymous"></script> -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	 crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	 crossorigin="anonymous"></script>


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
			
				<th scope='col' id="all">全选</th>
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
	
	<div class="xx">
		<button class="prevPage">上一页</button>
		<%PageModel pageModel = new PageModel(3); %>
		<%  for(int i = 0;i<pageModel.getTotalPageNum();i++){ %>
		<button class="pageButton" value="<%=i+1 %>">
			<%=i+1 %> </button>
		<% if(i==0||i==pageModel.getTotalPageNum()-2){ %>
		<span>...</span>
		<% } %>
		<% } %>
		<button class="nextPage">下一页</button>
	</div>
</body>


</html>