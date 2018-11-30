<%@ page import="Bean.Order" %>
<%@ page import="Bean.Dish_Order" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/myStyle.css">

    <title>支付宝网站支付</title>
    <%
        Order order=new Order();
        order.setORDERNO(String.valueOf(System.currentTimeMillis()));
        order.setPRICE(BigDecimal.valueOf(98));
        if(request.getSession().getAttribute("order")!=null)
            order=(Order)request.getSession().getAttribute("order");

    %>
</head>
<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4>


    </div>
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
    <div id="payForm">
        <form name=alipayment action=alipay.trade.page.pay.jsp method=post
              target="_blank" >
            <input type="hidden" name="alipay" value="pay">
            <div class="container">
                <div class="row">
                    <table class="table table-bordered table-hover">


                        <tr>

                            <td>
                                订单号 ：
                            </td>
                            <td>
                                <%=order.getORDERNO()%>
                                <input type="hidden" id="WIDout_trade_no" name="WIDout_trade_no" value="<%=order.getORDERNO()%>" readonly />

                            </td>
                        </tr>
                        <tr>

                            <td>
                                订单名称 ：
                            </td>
                            <td>
                                <%=order.getORDERNO()%>
                                <input type="hidden" id="WIDsubject" name="WIDsubject" value="<%=order.getORDERNO()%>"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                付款金额 ：
                            </td>
                            <td>
                                <%=order.getPRICE()%>
                                <input type="hidden" id="WIDtotal_amount" name="WIDtotal_amount" value="<%=order.getPRICE()%>" />
                            </td>
                        </tr>

                    </table>

                    <hr class="one_line">
                    <dd>
                        <input id="WIDbody" name="WIDbody" />
                    </dd>
                    <dd id="btn-dd">
						<span class="new-btn-login-sp">
							<button class="btn-primary" type="submit"
                                    style="text-align: center;" onclick="showQueryForm()">付 款</button>
						</span> <span class="note-help">如果您点击“付款”按钮，即表示您同意该次的执行操作。</span>
                    </dd>
                </div>
            </div>
        </form>
    </div>
        <div id="queryForm" style="display: none">
            <form name=tradequery action="/Alipay" method=post
                  target="_blank" >

                    <input type="hidden" id="WIDTQout_trade_no" name="WIDTQout_trade_no" value="<%=order.getORDERNO()%>" />
                    <input type="hidden" id="WIDTQtrade_no" name="WIDTQtrade_no" value="">
                    <input type="hidden" name="alipay" value="query">

						<span class="new-btn-login-sp">
							<button class="new-btn-login" type="submit"
                                    style="text-align: center;">我已支付，点击查询</button>

						</span>

            </form>
        </div>

    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


<script >
    function showQueryForm() {
        document.getElementById("payForm").style.display='none';
        document.getElementById("queryForm").style.display='block';
    }
</script>
</body>
</html>