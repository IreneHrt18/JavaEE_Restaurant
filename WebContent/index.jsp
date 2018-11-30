<%@page import="Bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: pepper
  Date: 2018/9/11
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.SQLException" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<html>

<head>
  <base href="<%=basePath%>">

  <title>My JSP 'login.jsp' starting page</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
    crossorigin="anonymous">

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->

</head>

<body style="background-image:url('./Image/disk.jpg'); background-size: 100% auto;">
  <nav class="navbar navbar-expand-lg navbar-light  bg-success">
    <a class="navbar-brand text-light" href="#">饱了吗</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav"
      aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse container-fluid" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item active">
          <a class="nav-link text-light" href="./CustomerJSP/RestaurantPage.jsp">菜品页 <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-light" href="./CustomerJSP/CartPage.jsp">购物车</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-light" href="./CustomerJSP/HistoryPage.jsp">热门</a>
        </li>
      </ul>
     <ul class="nav navbar-nav navbar-right">
                <li class="nav-item  ">
                <% 
                	User user = (User)request.getSession().getAttribute("user");
                	String value = "";
                	if(user == null){
                		value = "想要点餐就要登录哟~";
                	}else{
                		value = user.getUSERNAME();
                	}
                %>
                    <a class="disabled text-light" href="./LoginOrSignup.jsp"><%=value%></a>
                </li>
            </ul>
    </div>
  </nav>
  <div class="jumbotron p-3 p-md-5 text-light rounded bg-transparent">


    <div class="container-fluid px-0">
      <p class="lead my-3 text-right "> 欢迎来到</p>
      <h1 class="display-1 text-right">饱了吗</h1>
      <p class="lead mb-0 text-right">开启你的美食之旅......</p>
    </div>


  </div>

  </div>

</body>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
  crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
  crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
  crossorigin="anonymous"></script>

</html>