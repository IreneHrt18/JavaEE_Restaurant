package Servlet;

import java.io.IOException;
import java.util.ArrayList;

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
		switch (action) {
		case "search":
			String OrderNo = request.getParameter("searchText");
			response.getWriter().println(searchByDishNo(OrderNo));			
			break;
		case "load":
			response.getWriter().println(searchAllOrders());			
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
