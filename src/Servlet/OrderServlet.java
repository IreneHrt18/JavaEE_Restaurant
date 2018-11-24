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
	//��ת�������������
	private Order searchOrder(String OrderNo){
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO = new OrderIMPL();
		String[] params = {OrderNo};
		list = (ArrayList<Order>)searchDAO.searchByPrimaryKey(params);
		Order order=list.get(0);
		return order;
	}
	//��ʾ�������в�Ʒ
	private ArrayList<Dish> searchDishOfOrder(String OrderNo){
		ArrayList<Dish> list = new ArrayList<>();
		OrderIMPL orderDAO=new OrderIMPL();
		String []params= {OrderNo};
		list=orderDAO.searchDishOfOrder(params);
		return list; 
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
		String ordernumber = null;
		switch (action) {
		//����������ѯ������Ϣ��
		case "search":
			String OrderNo = request.getParameter("searchText");
			response.getWriter().println(searchByDishNo(OrderNo));			
			break;
		//��ʼ���������ж�����Ϣ��
		case "load":
			response.getWriter().println(searchAllOrders());
			break;
		case "statement":
			//��ȡ��ѯ������
			ordernumber=request.getParameter("ordernumber");
			Order order=searchOrder(ordernumber);
            request.setAttribute("order", order);
            //��ȡ��������
            ArrayList<Dish> list=searchDishOfOrder(ordernumber);
            request.setAttribute("dishlist", list);
            //��ת������������档
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
