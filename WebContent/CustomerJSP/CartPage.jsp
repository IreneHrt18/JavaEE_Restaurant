<%@ page import="DAOIMPL.CartIMPL" %>
<%@ page import="Bean.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.User" %>
<%--
  Created by IntelliJ IDEA.
  User: pepper
  Date: 2018/10/23
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="./myStyle.css">

    <title>购物车</title>
    <%
        CartIMPL cartIMPL=new CartIMPL();
        User user=(User)request.getSession().getAttribute("user");

        String userno[]={user.getUSERNO()};
        ArrayList<Cart> carts=cartIMPL.searchByPrimaryKey(userno);

        request.getSession().setAttribute("allCarts",carts);
        int totalPrice=0;

    %>

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
                    <a class="nav-link text-light" href="../CustomerJSP/RestaurantPage.jsp">商家页 <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" href="../CustomerJSP/CartPage.jsp">购物车</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" href="../CustomerJSP/HistoryPage.jsp">热门</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="nav-item  ">
                    <a class="disabled text-light" href="./LoginOrSignup.jsp">
                        <%=user.getUSERNAME() %></a>
                </li>
            </ul>
        </div>
    </nav>
    <div>
        <form action="../CartServlet" style="position: fixed;z-index:100;top: 50px;right: 50px;">
            <button class="btn btn-warning" type="submit" id="trunc" onclick="confirmTrunc()">清空购物车</button>
            <input type="hidden" name="trunc" value="true" />
        </form>
    </div>
    <div class="container">
        <div class="row">
            <table class="table table-bordered table-hover">
                <%
                int restid=0; int index=0;
                for (Cart cart :carts) {
                    index++;
                %>
                <tr>
                    <td>
                        <div class="container">
                            <div class="row align-items-center">
                                <div class="col-sm">
                                    <div class="card">
                                        <div class="card-body">
                                            <h6 class="card-title">
                                                <%=cart.getDishname()%>
                                            </h6>
                                            <h6 class="card-subtitle text-right">单价：
                                                <%=cart.getDish().getPRICE()%>元</h6>
                                            <form id="form<%=index%>" action="/CartServlet">
                                                <div class="input-group mb-3">
                                                    <div class="input-group-prepend">
                                                        <button type="button" class="btn btn-sm btn-success btn-outline-secondary"
                                                            id="minus<%=index%>" style="display: none" onclick="editAmountFun('<%=index%>',0)">-</button>
                                                    </div>
                                                    <input type="text" class="form-control input-sm" id="editAmount<%=index%>"
                                                        name="editAmount" value="<%=cart.getDishCount()%>" width="auto"
                                                        readonly>
                                                    <div class="input-group-prepend">
                                                        <button type="button" class="btn btn-sm btn-success btn-outline-secondary"
                                                            id="plus<%=index%>" style="display: none" onclick="editAmountFun('<%=index%>',1)">+</button>
                                                        <button type="submit" class="btn btn-success btn-outline-secondary"
                                                            id="editConfirm<%=index%>" style="display: none">提交</button>
                                                        <button type="button" class="btn btn-success btn-outline-secondary"
                                                            id="edit<%=index%>" onclick="showEdit('minus<%=index%>','plus<%=index%>','editConfirm<%=index%>','edit<%=index%>')">编辑</button>
                                                    </div>
                                                </div>
                                                <input type="hidden" name="dishid" value="<%=cart.getDISHNO()%>" />
                                                <input type="hidden" name="reedit" value="true" />
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
                <%
                    totalPrice+=cart.getPrice().intValue()*
                            cart.getDishCount().intValue();
                }
                request.getSession().setAttribute("totalPrice",totalPrice);
            %>
                <tr>
                    <td>
                        <label class="text-right">总计：<strong>
                                <%=totalPrice%></strong>元</label>
                    </td>
                </tr>
            </table>
            <form action="../CartServlet" style="position: fixed;z-index:100;bottom: 10px;right: 50px;">
                <button class="btn btn-warning" type="submit" id="settle">结算</button>
                <input type="hidden" name="settle" value="true" />
            </form>
        </div>

    </div>
    <script>
        function confirmTrunc() {
            if (confirm("是否清空购物车？"))
                document.getElementById("trunc").submit();
        }
        function showEdit(minusid, plusid, editConfirmid, editid) {
            document.getElementById(minusid).style.display = "block";
            document.getElementById(plusid).style.display = "block";
            document.getElementById(editConfirmid).style.display = "block";
            document.getElementById(editid).style.display = "none";
        }
        function editAmountFun(idIndex, isPlus) {

            var inputText = document.getElementById("editAmount" + idIndex);
            if (isPlus == 1)
                inputText.value++;
            else if (inputText.value > 1)
                inputText.value -= 1;
            else {
                if (confirm("确认删除该订单吗？")) {
                    inputText.value = 0;
                    document.getElementById("form" + idIndex).submit();
                }
            }
            // alert(value);
        }
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

</body>

</html>