<%@page import="JDBC.DAOFactory"%>
<%@page import="DAO.SearchDAO"%>
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

	<script src="https://code.jquery.com/jquery-3.1.1.min.js" charset="UTF-8"></script>
	<script src="DishManagementPageJS.js" charset="UTF-8" type="text/javascript"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	 crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	 crossorigin="anonymous"></script>
<style>
	tr{
		background-color: white;
		opacity: 0.6;
		margin-top: 10px;
		border-radius: 5px;		
		margin-bottom: 10px;
	}
</style>

</head>

<body style="background-image:url('../Image/disk.jpg'); background-size: 100% auto; ">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item "><a class="nav-link" href="OrderManagementPage.jsp">订单信息</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="./DishManagementPage.jsp">菜品信息</a>
				</li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
						 aria-haspopup="true" aria-expanded="false">
							热搜
						</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item">排行</a>
						</div>
					</li>
				</ul>
				<input class="form-control mr-sm-2" type="search" placeholder="菜品编号" aria-label="Search">
				<input type="button" class="btn btn-outline-success my-2 my-sm-0" id="searchButton" value="搜索">
			</form>
		</div>
	</nav>
	<table class="table" style="padding: 10px;">
		<thead>
			<tr style="text-align:center">
				<th scope='col'><input type="checkbox" id="all"></th>
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
		<button id="deleteDish" class="btn btn-primary" style="float: left; margin-left: 10px; ">删除</button>
		<button class="btn btn-primary" style="float: left; margin-left: 10px;" onclick="window.location.href='../DishServlet?action=jumpToDetail'";>添加</button>
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-end">
				<li class="page-item">
					<button class="prevPage page-link" style="margin-right: 5px">上一页</button>
				</li>
				<%	PageModel pageModel = (PageModel)request.getSession().getAttribute("pageModel"); %>
				<%  for(int i = 0;i<pageModel.getTotalPageNum();i++){ %>
				<li class="page-item">
					<button class="pageButton page-link" style="margin-right: 5px" value="<%=i+1 %>">
						<%=i+1 %>
					</button>
				</li>
				<% if(i==0||i==pageModel.getTotalPageNum()-2){ %>
				<span class="page-link" style="margin-right: 5px">...</span>
				<% } %>
				<% } %>
				<li class="page-item">
					<button class="nextPage page-link" style="margin-right: 5px">下一页</button>
				</li>
				</li>
			</ul>
		</nav>
	</div>
</body>


</html>