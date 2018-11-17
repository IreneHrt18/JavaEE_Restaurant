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
    //������ѯ��
	private String searchByDishNo(String OrderNo) {
		//ͨ���ӿڻ�ò�ѯ���dishlist
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO = new OrderIMPL();
		String[] params = {OrderNo};
		list = (ArrayList<Order>)searchDAO.searchByPrimaryKey(params);
		//��dishlistת����json����
		JSONArray jsonArray = new JSONArray();
		for(int i =0;i<list.size();i++) {
			jsonArray.add(JSONObject.fromObject(list.get(i)));
		}
		return jsonArray.toString();
	}
	//��ѯ���ж���
	private String searchAllOrders() {
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO = new OrderIMPL();
		list=(ArrayList<Order>)searchDAO.searchAll();
		//ת����ΪJSon
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
