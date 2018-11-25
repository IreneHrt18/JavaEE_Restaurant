<%@page import="java.util.ArrayList"%>
<%@page import="Bean.Dish"%>
<%@page language = "java" contentType = "text/html; charset=utf-8"
pageEncoding = "utf-8" %>

    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>菜品详情</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
            integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.min.js" type="text/javascript"></script>

    </head>
    <%      
    		ArrayList<Dish> list = (ArrayList<Dish>)request.getSession().getAttribute("dishList");
    		Dish currentDish = list.get(0);
    		request.getSession().removeAttribute("dishList");
    		//存储菜品编号
    		request.getSession().setAttribute("currentDish", currentDish.getDISHNO());
    %>
    <script>
        //存储图像连接
        var src;
        //绑定事件
        $(document).ready(function ready() {
            //图片点击事件，相当于执行button的点击事件
            $(document).on("click", "div img", function upload() {
                $(":file").click();
            });
            //绑定type = file 的change事件
            $(document).on("change", ":file", function change() {
                var url = window.URL.createObjectURL(document.getElementById("DishPicFile").files[0]);
                if (url) {
                    $("div img").attr("src", url);
                } else {
                    //提示错误
                    alert("上传错误，请重新选择!");
                    return false;
                }
                var formdata = new FormData();
                formdata.append('file', $("#DishPicFile")[0].files[0]);
                $.ajax({
                    type: "post",
                    url: "../DishServlet",
                    data: formdata,
                    dataType: "text",
                    processData: false, // 告诉jQuery不要去处理发送的数据
                    contentType: false, // 告诉jQuery不要去设置Content-Type请求头
                    success: function (response) {
						alert("上传成功");
                    }
                });
            });
        });        
    </script>

    <body>
        <div class="jumbotron p-3 p-md-2 text-white rounded bg-dark">
            <div class="container-fluid">
                <div class="row align-items-center">
                    <div class="col-4">
                        <h1 class="card-title">菜品信息</h1>
                        <h6 class="card-text">菜品内容</h6>
                        <h7 class="card-subtitle">菜品方案</h7>
                    </div>
                    <div class="col-7" style="width:200px;height:200px;overflow:hidden;">
                        <form action="../DishServlet?action=upload" id="imgForm" enctype="multipart/form-data">
                            <img class="card-img-top" src="<%=currentDish.getIMG() %>" alt="Card image cap" style="width:600px; height: 200px; cursor: pointer;">
                            <input type="file" class="form-control-file" id="DishPicFile" hidden multiple>
                            <input type="submit" hidden>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="card" style="align-self: center; width: 60%; height: 60%; position: relative;margin: 0 auto 0 auto;">
            <div>
                <form>
                    <div class="form-group">
                        <label for="exampleInputPassword1">菜品描述</label>
                        <input type="text" class="form-control" value="<%=currentDish.getDESCRIPTION() %>">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">菜品编号</label>
                        <input type="text" class="form-control" value="<%=currentDish.getDISHNO() %>">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">菜品名称</label>
                        <input type="text" class="form-control" value="<%=currentDish.getDISHNAME() %>">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">菜品价格</label>
                        <input type="text" class="form-control" value="<%=currentDish.getPRICE() %>">
                    </div>
                    <button type="submit" class="btn btn-primary">提交</button>
                </form>
            </div>
        </div>
    </body>

    </html>