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
        <%ArrayList<Order> userNameList=(ArrayList)request.getAttribute("orderList");
		Iterator<Order> user=userNameList.iterator();
		while(user.hasNext()) {
		Order order=user.next();%>
                <h1 class="card-title"><%= order.getUSERNAME()%>个人订单</h1><%} %>
                <h6 class="card-text">订单详情</h6>
                <h7 class="card-subtitle"></h7>
            </div>
            <div class="col-7" style="width:200px;height:200px;overflow:hidden;">
                <h1 class="card-title"></h1>
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
         
         <td style="text-align:center">
         <%if(order.getORDERSTATE().equals("未支付")) {%>
         <a href="#">去支付</a>
         <%} %>
         <%if(order.getORDERSTATE().equals("已支付")) { %>
         <%= order.getORDERSTATE() %>
         <%} %>
         </td>
         
         <td style="text-align:center"><%= order.getCOMMENTSTATE()%></td>
         <%}%>
		</tbody>
	</table>
	</div>
<div id="bodayview" style="background-color:beige;">

<div id="card">
	<%ArrayList<Dish> list=(ArrayList)request.getAttribute("dishlist"); 
	Iterator<Dish> i=list.iterator();
	while(i.hasNext()) {
		Dish dish=i.next();%>	   
	<div class="card" style="float:left; width: 70%; height: 150px; background-color: aliceblue;opacity: 0.8;margin: 15px;">        
        <div class="card-body">
            <div><img  style="float:left;" src="<%=dish.getIMG()%>" width='80px' height='80px'></div>
            <h5 style="position:relative; margin: auto auto auto 30%"><%= dish.getDISHNAME()%></h5>
            <div style="float:right;"><p class="card-text"><%= dish.getDESCRIPTION()%></p></div>
            <p style="position:relative; margin: auto auto auto 30%"><%= dish.getPRICE()%>￥</p>
		</div>
	</div>
	<%}%>
</div>

	           <div class="col-3" >
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    商家公告
                                </div>
                                <div class="card-body">
                                    <div class="card-text">
                                       写点什么
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

	
</body>
</html>