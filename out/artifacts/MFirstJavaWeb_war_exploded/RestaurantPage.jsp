<%@ page import="Model.Restaurant" %>
<%@ page import="DAO.RestaurantDAOIpt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: pepper
  Date: 2018/10/21
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商家页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/myStyle.css">

    <style type="text/css">
        .jumbotron{
            background-image: url('<%=request.getContextPath()%>/res/shrimp.jpg');
            background-size: 100% auto;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light  bg-transparent">
    <a class="navbar-brand text-success" href="#">饱了吗</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse container-fluid" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link text-success" href="<%=request.getContextPath()%>/RestaurantPage.jsp">商家页 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-success" href="<%=request.getContextPath()%>/CartPage.jsp">购物车</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-success" href="<%=request.getContextPath()%>/HistoryPage.jsp">热门</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-warning disabled" href="<%=request.getContextPath()%>/UsersPage/LoginOrSignup.jsp">登陆/注册</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item ">
                <a class="disabled"  href="<%=request.getContextPath()%>/UsersPage/LoginOrSignup.jsp" >欢迎回来，<%=request.getSession().getAttribute("username")%></a>
            </li>
        </ul>
    </div>
</nav>
<%
    ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
    try {
        restaurants=new RestaurantDAOIpt().allRestaurant();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    %>
<div class="jumbotron p-3 p-md-5 text-white rounded bg-dark" >
    <div class="col-md-6 px-0">
        <h1 class="display-4 font-italic">饱了吗每天给你不一样的饥饿感</h1>
        <p class="lead my-3"> 你可以选择任何你想吃的，只要我们能送到</p>
        <p class="lead mb-0"><a href="#" class="text-white font-weight-bold">向下解锁你的吃货旅程...</a></p>
    </div>
</div>
<div class="container" >

    <div class="row mb-2">
        <table class="table table-bordered table-hover">
            <%
                for(int i=0;i<restaurants.size();i++)
                {
                    Restaurant restaurant=restaurants.get(i);
            %>
            <tr>

                <td>
                    <div class="card container">
                        <div class="row align-items-center">
                            <div class="col-sm-auto">
                                <img  class="card-img-left" src="<%=request.getContextPath()%>/res/rest.jpg" alt="图片无法显示">

                            </div>
                            <div class="col-sm-6">
                                <div class="card-body">
                                    <h6 class="card-title"><%= restaurant.getName()%></h6>
                                    <p><%= restaurant.getAddress()%> </p>
                                    <h8 class="card-sub-tile"><%= restaurant.getPhone()%></h8>

                                </div>
                            </div>
                            <div class="col-sm-3">
                                <a  class="btn btn-success text-right" href="GetMenues.jsp?restid=<%= restaurant.getRestaurantid()%>" >进入商家</a>
                            </div>
                        </div>
                    </div>

                </td>
            </tr>
            <%

                }
            %>
        </table>
    </div>

</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>
