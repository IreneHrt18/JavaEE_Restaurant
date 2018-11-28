<%@ page import="com.alipay.api.AlipayResponse" %>
<%@ page import="com.alipay.api.response.AlipayTradePrecreateResponse" %><%--
  Created by IntelliJ IDEA.
  User: pepper
  Date: 2018/11/14
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付结果</title>
</head>
<body>
<%
    AlipayResponse alipayResponse= (AlipayResponse) request.getAttribute("resultQuery");

%>
code:<%=alipayResponse.getCode()%>
msg:<%=alipayResponse.getMsg()%>
submsg:<%=alipayResponse.getSubMsg()%>
body:<%=alipayResponse.getBody()%>
</body>
</html>
