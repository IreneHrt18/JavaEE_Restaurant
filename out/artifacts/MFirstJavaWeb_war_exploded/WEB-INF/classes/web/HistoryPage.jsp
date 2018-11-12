<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.History" %>
<%@ page import="DAO.HistoryDAOIpt" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: pepper
  Date: 2018/10/23
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/myStyle.css">

    <title>热门</title>
    <%
        ArrayList<History> histories= null;
        try {
            histories = new HistoryDAOIpt().selHistory();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    %>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light  bg-success">
    <a class="navbar-brand text-light" href="#">饱了吗</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse container-fluid" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link text-light" href="<%=request.getContextPath()%>/RestaurantPage.jsp">商家页 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-light" href="<%=request.getContextPath()%>/CartPage.jsp">购物车</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-light" href="<%=request.getContextPath()%>/HistoryPage.jsp">热门</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="<%=request.getContextPath()%>/UsersPage/LoginOrSignup.jsp">登陆/注册</a>
            </li>
        </ul>
        <ul class=" nav navbar-nav navbar-right">

            <li class="nav-item navbar-right">
                <a class="disabled"  href="<%=request.getContextPath()%>/UsersPage/LoginOrSignup.jsp" >欢迎回来，<%=request.getSession().getAttribute("username")%></a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="row">
        <table class="table table-bordered table-hover">
            <tr>
                <td>
                    <h6 class="text-center">热度</h6>
                </td>
                <td>
                    <h6 class="text-center">商品</h6>
                </td>
            </tr>
            <%
                int pop=1;
                for (History history :
                        histories) {
            %>
            <tr>
                <td>
                    <h7><%=pop++%></h7>
                </td>
                <td>
                    <div class="container">
                        <div class="row align-items-center">
                            <div class="col-sm-auto">
                                <img class="card-img-left" src="<%=request.getContextPath()%>/res/rest.jpg">
                            </div>
                            <div class="col-sm">
                                <div class="card">
                                    <div class="card-body">
                                        <h6 class="card-title"><%=history.getMenu().getDish().getName()%></h6>
                                        <a class="card-subtitle" href="GetMenues.jsp?restid=<%=history.getMenu().getRestaurant().getRestaurantid()%>"><%=history.getMenu().getRestaurant().getName()%></a>
                                    </div>
                                </div>
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
