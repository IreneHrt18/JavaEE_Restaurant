package Servlet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Dish;
import Bean.Order;
import DAO.SearchDAO;
import DAOIMPL.DishIMPL;
import DAOIMPL.OrderIMPL;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //查询订单信息
	private String searchByOrderNo(String OrderNo) {
		//ͨ初始化列表
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO = new OrderIMPL();
		String[] params = {OrderNo};
		list = (ArrayList<Order>)searchDAO.searchByPrimaryKey(params);
		//初始化JSON数组
		JSONArray jsonArray = new JSONArray();
		for(int i =0;i<list.size();i++) {
			jsonArray.add(JSONObject.fromObject(list.get(i)));
		}
		return jsonArray.toString();
	}
	//通过主键查询订单
	private ArrayList<Order> searchCurrentOrder(String OrderNo){
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO = new OrderIMPL();
		String[] params = {OrderNo};
		list = (ArrayList<Order>)searchDAO.searchByPrimaryKey(params);
		return list;
	}
	//查询当前订单内的菜品信息。
	private ArrayList<Dish> searchDishOfOrder(String OrderNo){
		ArrayList<Dish> list = new ArrayList<>();
		OrderIMPL orderDAO=new OrderIMPL();
		String []params= {OrderNo};
		list=orderDAO.searchDishOfOrder(params);
		return list; 
	}
	//查询所有订单信息
	private String searchAllOrders() {
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO = new OrderIMPL();
		list=(ArrayList<Order>)searchDAO.searchAll();
		//生成JSON数组。
		JSONArray jsonArray=new JSONArray();
		for(int i =0;i<list.size();i++) {
			jsonArray.add(JSONObject.fromObject(list.get(i)));
		}
		return jsonArray.toString();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String ordernumber = null;
		switch (action) {
		//所有订单界面的查询
		case "search":
			String OrderNo = request.getParameter("searchText");
			response.getWriter().println(searchByOrderNo(OrderNo));			
			break;
		//所有订单界面的加载
		case "load":
			response.getWriter().println(searchAllOrders());
			break;
		case "statement":
			//获取订单号
			ordernumber=request.getParameter("ordernumber");
			ArrayList<Order> orderList=searchCurrentOrder(ordernumber);
            request.setAttribute("orderList", orderList);
            //查询订单中的菜品
            ArrayList<Dish> list=searchDishOfOrder(ordernumber);
            request.setAttribute("dishlist", list);
            //跳转订单详情业
        	RequestDispatcher rDispatcher=request.getRequestDispatcher("./MerchantJSP/OrderStatement.jsp");
        	rDispatcher.forward(request, response);	
			break;

		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
