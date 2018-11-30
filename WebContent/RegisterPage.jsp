<%--
  Created by IntelliJ IDEA.
  User: pepper
  Date: 2018/10/15
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@page import="Bean.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
    <title>注册</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>
        $(document).ready(function () {
            $(document).on("change", "[name='username']", function () {
                if ($(this).val() == "") {
                    $("#username").html("用户名不能为空");
                } else {
                    jQuery.ajax({
                        type: "post",
                        url: "./SignUpServlet",
                        data: "action=verify&username=" + $("[name=username]").val(),
                        dataType: "text",
                        success: function (response) {
                            $("#username").html(response);
                        }
                    });
                }
            });
        });

    </script>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-light  bg-success">
        <a class="navbar-brand text-light" href="#">饱了吗</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav"
            aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse container-fluid" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link text-light" href="./CustomerJSP/RestaurantPage.jsp">商家页 <span class="sr-only">(current)</span></a>
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
                    <a class="disabled text-light" href="./LoginOrSignup.jsp">
                        <%=value%></a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container  form-con">
        <div class="row">
        </div>
        <div class="row">
            <div class="col-sm">
            </div>
            <div class="col-sm-6">
                <div class="container border shadow-lg p-3 mb-5 bg-white rounded bg-light ">
                    <form class="form-horizontal" role="form" method="get" action="SignUpServlet">
                        <div class="form-group">
                            <label>新用户名：</label>
                            <div class="col-sm" style="align-content: center; ">
                                <input type="text" class="form-control" name="username">
                                <label id="username"></label>
                            </div>

                        </div>
                        <div class="form-group">
                            <label>密码：</label>
                            <div class="col-sm" style="align-content: center; ">
                                <input type="password" class="form-control" name="password">
                                <label id="password"></label>
                            </div>

                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success btn-lg btn-block">注册</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-sm">
            </div>
        </div>
        <div class="row">
        </div>
    </div>
    <!--     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
    <!--         crossorigin="anonymous"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

</body>

</html>