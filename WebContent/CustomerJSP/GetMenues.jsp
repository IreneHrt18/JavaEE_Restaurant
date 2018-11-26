<%@ page import="Bean.Dish" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DAOIMPL.DishIMPL" %><%--
  Created by IntelliJ IDEA.
  User: pepper
  Date: 2018/10/22
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/myStyle.css">
    <title>选择商品</title>
    <style type="text/css">
        .jumbotron{
            background-image: url('<%=request.getContextPath()%>/res/cooking.jpg');
            background-size: 100% auto;
        }
    </style>
    <%!
        ArrayList<Dish> dishes;
        DishIMPL dishIMPL=new DishIMPL();
        int order;
    %>
    <%

        order=Dish.DishCompar.BY_NAME;
        //查询该餐厅下所有菜单
        //如果第一次加载则查询，并在会话中设置参数“firstPost”为false,orderBy参数为默认，
        //并把dishes放入session中
        // 否则从会话中取得dishes
        if(request.getSession(true).getAttribute("firstPost").equals(true))
        {
            dishes=dishIMPL.searchAll();
            dishes=dishIMPL.sortAll(dishes,Dish.DishCompar.BY_NAME);

            request.getSession(false).setAttribute("menus",dishes);
            request.getSession(false).setAttribute("firstPost",false);
        }
        else {

            dishes = (ArrayList<Dish>) request.getSession().getAttribute("menus");
            order=Integer.parseInt(request.getParameter("dishOrderBy")==null?"0":request.getParameter("dishOrderBy"));
            dishes=dishIMPL.sortAll(dishes,order);
        }
    %>

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
            <li class="nav-item">
                <a class="disabled"  href="<%=request.getContextPath()%>/UsersPage/LoginOrSignup.jsp" >欢迎回来，<%=request.getSession().getAttribute("username")%></a>
            </li>
        </ul>
    </div>
</nav>
<div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">

        <div class="container-fluid">
        <div class="row align-items-center">
            <div class="col-6">
                <h1 class="card-title">商家名称</h1>
                <h6 class="card-text">地址：</h6>
                <h7 class="card-subtitle">电话：</h7>
            </div>
            <div class="col-3">
                <h6>配送费：元</h6>
            </div>
            <div class="col-3">
                <h6>送达时间：分钟</h6>
            </div>
        </div>


    </div>

</div>

<div class="container">
    <div class="row">
        <div class="col-8">
            <table class="table table-bordered table-hover">
                <tr>
                    <td>

                        <a href="GetMenues.jsp?dishOrderBy=<%=order==1?3:1%>">名称<%=order==1?"<":">"%></a>
                        <a href="GetMenues.jsp?dishOrderBy=<%=order==2?0:2%>">价格<%=order==2?"<":">"%></a>

                    </td>

                </tr>
                <%
                    int row=0;
                    for (Dish menu :
                            dishes) {

                        row++;
                %>
                <tr>
                    <td class="menuRow" style="display: none">
                        <div class="card container " >
                            <div class="row align-items-center">
                                <div class="col-sm-auto">
                                    <img class="card-img-left" src="<%=request.getContextPath()%>/<%=menu.getIMG()%>" >

                                </div>
                                <div class="col-sm-7">
                                    <div class="card-body" >
                                        <h6 class="card-title"><%=menu.getDISHNAME()%></h6>
                                        <h8 class="card-subtitle text-danger">¥<%=menu.getPRICE()%></h8>
                                        <a class="btn btn-sm" data-toggle="modal" data-target="#myModal<%=row%>">详情</a>
                                        <div class="modal fade" id="myModal<%=row%>" role="dialog" aria-labelledby="myModalLabel<%=row%>" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="myModalLabel<%=row%>">
                                                            <%=menu.getDISHNAME()%>
                                                        </h4>
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                            &times;
                                                        </button>
                                                    </div>
                                                    <div class="modal-body container">
                                                        <div class="row">
                                                            <div class="col-8">
                                                                <img width="300px" height="300px" src="<%=request.getContextPath()%>/<%=menu.getIMG()%>">
                                                            </div>
                                                            <div class="col-4">
                                                                <h7><%=menu.getDESCRIPTION()%></h7>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal -->
                                        </div>
                                    </div>

                                </div>
                                <div class="col-sm-1">
                                    <div class="card-body" >
                                        <a class="btn btn-success" href="/CartServlet?dishid=<%=menu.getDISHNO()%>&dishOrderBy=<%=order%>" >添加到购物车</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 模态框（Modal） -->

                    </td>
                </tr>
                <%
                    }
                %>
            </table>
            <button class="btn btn-success" onclick="checkPage(0)">前一页</button>
            <button class="btn btn-success" onclick="checkPage(1)">后一页</button>
        </div>
        <div class="col-4">
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
    </div >

</div>
<script >
    var pageIndex=1;
    var menuBlock=6;
    console.log(document.getElementsByClassName("menuRow").length);
    for(var i=0;i<menuBlock;i++){
        var row=document.getElementsByClassName("menuRow").item(i);
        row.style.display="block";
    }
    function checkPage( isAfter){
        for(var i=0;i<<%=dishes.size()%>;i++){
            var row=document.getElementsByClassName("menuRow")[i];
            row.style.display="none";
        }
        if(!isAfter&&pageIndex>1)
            pageIndex--;
        else if(!isAfter)
        {alert("已经是第一页！");

        }
        //保证在刚好整除时不会出现空页
        else if(pageIndex >(<%=dishes.size()%>+menuBlock-1)/menuBlock)
        {
            alert("已经是最后一页！");
        }
        else
            pageIndex++;

        for(var i=(pageIndex-1)*menuBlock;i<pageIndex*menuBlock;i++){
            var row=document.getElementsByClassName("menuRow")[i];
            row.style.display="block";
        }

    }

</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>
