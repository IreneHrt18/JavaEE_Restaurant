package Servlet;


import Bean.*;
import DAOIMPL.CartIMPL;
import DAOIMPL.OrderIMPL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String editAmount=req.getParameter("editAmount");

        if(req.getParameter("trunc")!=null)
            trunc(req, resp);
        else if(req.getParameter("settle")!=null)
            settle(req,resp);
        else if(editAmount==null && req.getParameter("reedit")==null) {
        	addToCart(req,resp);
        	resp.sendRedirect("./CustomerJSP/GetMenues.jsp");
        	return;
        }
        else
            modifyCart(req,resp);
    }

    private void settle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Order order =new Order();
        User user=(User)req.getSession().getAttribute("user");
        order.setORDERNO(String.valueOf(System.currentTimeMillis()));
        order.setUSERNO(user.getUSERNO());
        order.setORDERSTATE("未支付");
        order.setCOMMENTSTATE("未评论");
        order.setPRICE(BigDecimal.valueOf((Integer)req.getSession().getAttribute("totalPrice")));
        order.setTIME(new Date());
        ArrayList<Cart> carts=(ArrayList<Cart>)req.getSession().getAttribute("allCarts");
        if(carts.size()<=0) {
        	resp.getWriter().println("<script>"
        			+ " alert('您的购物车是空的哟，请添加商品到你的购物车吧~'); "
        			+" window.location.href='./CustomerJSP/CartPage.jsp'; "
        			+ "</script>");
        	return;
        }
        for (Cart cart :
                carts) {
            Dish_Order dish = new Dish_Order();
            dish.setOrderno(order.getORDERNO());
            dish.setDishno(cart.getDISHNO());
            dish.setDishcount(cart.getDishCount());
            dish.setComments("");
            order.getDishes().add(dish);
        }
        new OrderIMPL().addObj(order);
        resp.sendRedirect("./PersonalServlet?action=statment&ordernumber="+order.getORDERNO());
        return;
    }

    private void trunc(HttpServletRequest req, HttpServletResponse resp) {
        new CartIMPL().trucOrderList();
        toCartPage(req, resp);
    }

    private void toCartPage(HttpServletRequest req, HttpServletResponse resp) {
        try {

            RequestDispatcher requestDispatcher=req.getRequestDispatcher("./CustomerJSP/CartPage.jsp");
            requestDispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        addToCart(req, resp);

    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) {

        String dishid= req.getParameter("dishid");
        Cart cart=new Cart();
        cart.setDishno(dishid);
        User user=(User)req.getSession().getAttribute("user");
        cart.setUserno(user.getUSERNO());
        cart.setDishCount(BigDecimal.valueOf(1));
        CartIMPL cartIMPL= new CartIMPL();
        cartIMPL.addObj(cart);
    }

    private void modifyCart(HttpServletRequest req, HttpServletResponse resp)
    {
        CartIMPL cartIMPL=new CartIMPL();
        String dishid= req.getParameter("dishid");
        String editAmount=req.getParameter("editAmount");
        BigDecimal amount=BigDecimal.valueOf(Integer.parseInt(editAmount));

        User user=(User)req.getSession().getAttribute("user");
        //new OrderDAOIpt().addOrder();
        Cart cart=new Cart();
        cart.setDishno(dishid);
        cart.setDishCount(amount);
        cart.setUserno(user.getUSERNO());
        //Order order=new Order(menu,1,Integer.parseInt(getServletContext().getInitParameter("userid")));

        if(amount.intValue()>0)
            cartIMPL.alterObj(cart);
        else
            cartIMPL.deleteObj(cart);


        toCartPage(req, resp);
    }
}