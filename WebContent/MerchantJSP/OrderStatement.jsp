<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="Bean.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>订单详情</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	 crossorigin="anonymous">
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>


</head>
<body>
<div class="jumbotron p-3 p-md-2 text-white rounded bg-dark">
    <div class="container-fluid">
        <div class="row align-items-center">
            <div class="col-4">
                <h1 class="card-title">订单详情</h1>
                <h6 class="card-text">菜品内容</h6>
                <h7 class="card-subtitle">菜品方案</h7>
            </div>
            <div class="col-7" style="width:200px;height:200px;overflow:hidden;">
                <h1 class="card-title">订单详情</h1>
            </div>
        </div>
    </div>
</div>
<div class="alert alert-info" role="alert">
	<table class="table">
		<thead>
			<tr>
				<th scope="col" style="text-align:center">订单号</th>
				<th scope="col" style="text-align:center">用户编号</th>
				<th scope="col" style="text-align:center">用户姓名</th>
				<th scope="col" style="text-align:center">消费金额</th>
				<th scope="col" style="text-align:center">订单时间</th>
				<th scope="col" style="text-align:center">订单状态</th>
				<th scope="col" style="text-align:center">是否评论</th>
			</tr>
		</thead>
		
		<tbody id="dishlist">
		<%ArrayList<Order> orderList=(ArrayList)request.getAttribute("orderList");
		Iterator<Order> j=orderList.iterator();
		while(j.hasNext()) {
		Order order=j.next();%>
         <td scope='row' style="text-align:center"><%=order.getORDERNO()%></td>
         <td style="text-align:center"><%= order.getUSERNO()%></td>
         <td style="text-align:center"><%= order.getUSERNAME()%></td>
         <td style="text-align:center"><%= order.getPRICE()%></td>
         <td style="text-align:center"><%= order.getTIME()%></td>
         <td style="text-align:center"><%= order.getORDERSTATE() %></td>
         <td style="text-align:center"><%= order.getCOMMENTSTATE()%></td>
         <%}%>
		</tbody>
	</table>
	</div>
	<div class="alert alert-primary">
	<%ArrayList<Dish> list=(ArrayList)request.getAttribute("dishlist"); 
	Iterator<Dish> i=list.iterator();
	while(i.hasNext()) {
		Dish dish=i.next();%>	   
	    <div class="card" style="width: 70%; height: 100px;">        
        <div class="card-body">
        <div style="float:left; width:60px; height:50px;"><img class="card-img-top" src="bg.jpg" alt="Card image cap"></div>
        <h5 class="card-title"><%= dish.getDISHNAME()%></h5>
        <div style="float:right; width: 100px; height: 100px"><p class="card-text"><%= dish.getDESCRIPTION()%></p></div>
         <p class="card-text"><%= dish.getPRICE()%></p>
		</div>
	</div>
	<%}%>
	
	</div>
	
</body>
</html>