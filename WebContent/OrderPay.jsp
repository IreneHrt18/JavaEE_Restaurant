<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>支付宝网站支付</title>

</head>
<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4>
	<header class="am-header">
	<h1>支付宝电脑网站支付体验入口页</h1>
	</header>
    <div id="payForm">
        <form name=alipayment action=alipay.trade.page.pay.jsp method=post
              target="_blank" >
            <input type="hidden" name="alipay" value="pay">
            <dt>商户订单号 ：</dt>
            <dd>
                <%
                    String trade_no= String.valueOf(System.currentTimeMillis());
                %>
                <input id="WIDout_trade_no" name="WIDout_trade_no" value="<%=trade_no%>" readonly />
            </dd>
            <hr class="one_line">
            <dt>订单名称 ：</dt>
            <dd>
                <input id="WIDsubject" name="WIDsubject" />
            </dd>
            <hr class="one_line">
            <dt>付款金额 ：</dt>
            <dd>
                <input id="WIDtotal_amount" name="WIDtotal_amount" />
            </dd>
            <hr class="one_line">
            <dt>商品描述：</dt>
            <dd>
                <input id="WIDbody" name="WIDbody" />
            </dd>
            <hr class="one_line">

            <dd id="btn-dd">
						<span class="new-btn-login-sp">
							<button class="new-btn-login" type="submit"
                                    style="text-align: center;" onclick="showQueryForm()">付 款</button>
						</span> <span class="note-help">如果您点击“付款”按钮，即表示您同意该次的执行操作。</span>
            </dd>
        </form>
    </div>
    <div id="queryForm" style="display: none">
        <form name=tradequery action="/Alipay" method=post
              target="_blank" >

            <dd>
                <input type="hidden" id="WIDTQout_trade_no" name="WIDTQout_trade_no" value="<%=trade_no%>" />
                <input type="hidden" id="WIDTQtrade_no" name="WIDTQtrade_no" value="">
                <input type="hidden" name="alipay" value="query">
            </dd>

            <dd id="btn-dd">
						<span class="new-btn-login-sp">
							<button class="new-btn-login" type="submit"
                                    style="text-align: center;">我已支付，点击查询</button>
						</span>
            </dd>
            </dl>
        </form>
    </div>

    </div>


<script >
    function showQueryForm() {
        document.getElementById("payForm").style.display='none';
        document.getElementById("queryForm").style.display='block';
    }
</script>
</body>
</html>