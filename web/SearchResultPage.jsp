<%@ page import="Model.Menu" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Dish" %>
<%@ page import="Model.Restaurant" %>
<%@ page import="DAO.DishDAOIpt" %>
<%@ page import="DAO.RestaurantDAOIpt" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="DAO.MenuDAOIpt" %>
<%@ page import="DAO.MenuDAOI" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="Servelet.OracleHelper" %><%--
  Created by IntelliJ IDEA.
  User: pepper
  Date: 2018/11/4
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜寻到以下结果</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/myStyle.css">

    <%!
        String searchString;
        Dish dishTem;
        Restaurant restaurantTem;
        ArrayList <Dish> dishes;
        ArrayList<Restaurant> restaurants;
        ArrayList<Menu> menuTems;
        ArrayList<Menu> menus;
        Menu menuTeple;
        MenuDAOI menuDAOI;
    %>
    <%
        menuDAOI=new MenuDAOIpt();
         searchString=request.getParameter("searchString");

        //dishid设为区域外值,price不影响搜索
        dishTem=new Dish(-1,searchString,0,null,null);
        restaurantTem=new Restaurant(searchString,searchString);


        try {
            dishes=new DishDAOIpt().selDish(dishTem);
            restaurants=new RestaurantDAOIpt().selRestaurant(restaurantTem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Dish dish :
                dishes) {
            menuTems.add(new Menu(null, dish));
        }
        for (Restaurant restaurant :
                restaurants) {
            menuTems.add(new Menu(restaurant, null));
        }

        menus=new ArrayList<Menu>();

        for (Menu menu :
                menuTems) {
            try {
                ArrayList<Menu> menusTem=menuDAOI.selMenu(menu, 0);
                if(menusTem!=null)
                menus .addAll(menusTem);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        int order=Integer.parseInt(request.getParameter("order")==null?"0":request.getParameter("order"));
    %>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light  bg-success">
    <a class="navbar-brand text-light" href="#">饱了吗</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
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
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-8">
            <table class="table table-bordered table-hover">

                <%
                    for (Menu menu :
                            menus) {
                        int row=0;
                %>
                <tr>
                    <td class="menuRow" style="display: none">
                        <div class="card container " >
                            <div class="row align-items-center">
                                <div class="col-sm-auto">
                                    <img class="card-img-left" src="<%=request.getContextPath()%>/res/rest.jpg">

                                </div>
                                <div class="col-sm-7">
                                    <div class="card-body">
                                        <h6 class="card-title"><%=menu.getDish().getName()%></h6>
                                        <h8 class="card-subtitle"><%=menu.getDish().getPrice()%></h8>
                                    </div>
                                </div>
                                <div class="col-sm-1">
                                    <div class="card-body" >
                                        <a class="btn btn-success" href="/GetMenues.jsp?&restid=<%=menu.getRestaurant().getRestaurantid()%>" >转到商家</a>
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
            <button class="btn btn-success" onclick="checkPage(0)">前一页</button>
            <button class="btn btn-success" onclick="checkPage(1)">后一页</button>
        </div>

    </div >

</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>
