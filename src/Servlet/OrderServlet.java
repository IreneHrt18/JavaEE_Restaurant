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
    //主键查询。
	private String searchByDishNo(String OrderNo) {
		//通过接口获得查询后的dishlist
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO = new OrderIMPL();
		String[] params = {OrderNo};
		list = (ArrayList<Order>)searchDAO.searchByPrimaryKey(params);
		//将dishlist转换成json数组
		JSONArray jsonArray = new JSONArray();
		for(int i =0;i<list.size();i++) {
			jsonArray.add(JSONObject.fromObject(list.get(i)));
		}
		return jsonArray.toString();
	}
	//跳转到订单详情界面
	private Order searchOrder(String OrderNo){
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO = new OrderIMPL();
		String[] params = {OrderNo};
		list = (ArrayList<Order>)searchDAO.searchByPrimaryKey(params);
		Order order=list.get(0);
		return order;
	}
	//显示订单所有菜品
	private ArrayList<Dish> searchDishOfOrder(String OrderNo){
		ArrayList<Dish> list = new ArrayList<>();
		OrderIMPL orderDAO=new OrderIMPL();
		String []params= {OrderNo};
		list=orderDAO.searchDishOfOrder(params);
		return list; 
	}
	//查询所有订单
	private String searchAllOrders() {
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO = new OrderIMPL();
		list=(ArrayList<Order>)searchDAO.searchAll();
		//转换华为JSon
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
		//按照主键查询订单信息。
		case "search":
			String OrderNo = request.getParameter("searchText");
			response.getWriter().println(searchByDishNo(OrderNo));			
			break;
		//初始化加载所有订单信息。
		case "load":
			response.getWriter().println(searchAllOrders());
			break;
		case "statement":
			//获取查询订单号
			ordernumber=request.getParameter("ordernumber");
			Order order=searchOrder(ordernumber);
            request.setAttribute("order", order);
            //获取订单详情
            ArrayList<Dish> list=searchDishOfOrder(ordernumber);
            request.setAttribute("dishlist", list);
            //跳转到订单详情界面。
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
