<%--
  Created by IntelliJ IDEA.
  User: pepper
  Date: 2018/10/15
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>用户登陆</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
    <style>
        .form-con {
            margin-top: 60px;
            padding-top: 60px;
            padding-bottom: 30px;
            padding-left: 60px;
            padding-right: 60px;
        }

        .form-horizontal {
            padding: 30px;
        }

        body {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-align: center;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }

        .form-signin .checkbox {
            font-weight: 400;
        }

        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }

        .form-signin .form-control:focus {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="./CustomerJSP/signin.css" type="text/css">

</head>

<body class="text-center">
    <form class="form-signin" method="post" action="Login">

        <h1 class="h3 mb-3 font-weight-normal">请登录</h1>
        <label for="inputEmail" class="sr-only">用户名</label>
        <input id="inputEmail" name="username" class="form-control" placeholder="输入用户名" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="输入密码" required>

        <label>
            <h8 class="text-danger">
                <%=request.getSession().getAttribute("failedLogin")==null?"":request.getSession().getAttribute("failedLogin")%>
            </h8>
            <%
                request.getSession().setAttribute("failedLogin"," ");
            %>
        </label>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
        </div>
        <button class="btn btn-lg btn-success btn-block" type="submit">登陆</button>
        <button class="btn btn-lg btn-success btn-block" onclick="window.location.href='./RegisterPage.jsp'">注册</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
</body>

</html>