package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Dish;
import Bean.Order;
import Bean.PageModel;
import DAO.SearchDAO;
import DAO.SortDAO;
import DAOIMPL.OrderIMPL;
import JDBC.DAOFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PersonalServlet
 */
@WebServlet("/PersonalServlet")
public class PersonalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * 查询当前订单的菜品信息。
     * @param OrderNo
     * @return
     */
	private ArrayList<Dish> searchDishOfOrder(String OrderNo){
		ArrayList<Dish> list = new ArrayList<>();
		OrderIMPL orderDAO=new OrderIMPL();
		String []params= {OrderNo};
		list=orderDAO.searchDishOfOrder(params);
		return list; 
	}
    /**
     * 通过主键查询当前用户订单信息。
     * @param OrderNo
     * @return
     */
	private String searchByOrderNo(String[] params) {
		//ͨ初始化列表
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO =(SearchDAO)DAOFactory.newInstance("Order");
		list = (ArrayList<Order>)searchDAO.searchByParams(params);
		//初始化JSON数组
		JSONArray jsonArray = new JSONArray();
		for(int i =0;i<list.size();i++) {
			jsonArray.add(JSONObject.fromObject(list.get(i)));
		}
		return jsonArray.toString();
	}
	/**
	 * 分页查询当前用户订单信息。
	 * @param request
	 * @param response
	 * @param isSort
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String searchOrdersByPage(HttpServletRequest request,HttpServletResponse response,boolean isSort,String userNo) {
		SearchDAO searchDAO = (SearchDAO)DAOFactory.newInstance("Order");
		//查询页面第一页的所有菜品信息
		int currenPage = Integer.parseInt(request.getParameter("currentPage"));
		ArrayList<Order> list = (ArrayList<Order>)searchDAO.searchByPage(currenPage, PageModel.getPageSize(),userNo);
		if(isSort) {
			SortDAO sortDAO = (SortDAO)DAOFactory.newInstance("Order");
			Order[] dishs = (Order[])list.toArray();
			list.clear();
			list.addAll(sortDAO.descSort(dishs));
		}
		JSONArray jsonArray = new JSONArray();
		for(int i =0;i<list.size();i++) {
			jsonArray.add(JSONObject.fromObject(list.get(i)));
		}
		return jsonArray.toString();
	}
	/**
	 * 获取个人订单界面的pagemodel
	 * @param request
	 * @param response
	 * @return
	 */
	private String getOrderPageModel(HttpServletRequest request,HttpServletResponse response,String userNO) {
		SearchDAO searchDAO = (SearchDAO)DAOFactory.newInstance("Order");
		//分页信息
		PageModel pageModel = new PageModel(searchDAO.getCount(userNO));
		JSONObject jsonObject = JSONObject.fromObject(pageModel);
		request.setAttribute("DishPageModel", pageModel);
		return jsonObject.toString();
	}
	/**
	 * 查询当前订单信息。
	 * @param OrderNo
	 * @param userNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<Order> searchCurrentOrder(String OrderNo,String userNo){
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO = (SearchDAO)DAOFactory.newInstance("Order");
		String[] params = {userNo,OrderNo};
		list = (ArrayList<Order>)searchDAO.searchByParams(params);
		return list;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		//String UserNo=request.getParameter("userNO");
		String UserNo="1001";
		//获得要查询的订单号。
		String ordernumber = null;
		switch (action) {
		//所有订单界面的查询
		case "search":
			String OrderNo = request.getParameter("searchText");
			response.setCharacterEncoding("utf-8");
			String[] params= {UserNo,OrderNo};
			response.getWriter().println(searchByOrderNo(params));			
			break;
		case "getPageModel":
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(getOrderPageModel(request,response,UserNo));
			break;
		case "searchByPage":
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(searchOrdersByPage(request, response, false, UserNo));
			break;
		case "statement":
			//获取订单号
			ordernumber=request.getParameter("ordernumber");
			ArrayList<Order> orderList=searchCurrentOrder(ordernumber,UserNo);
            request.setAttribute("orderList", orderList);
            //查询订单中的菜品
            ArrayList<Dish> dishList=searchDishOfOrder(ordernumber);
            request.setAttribute("dishlist", dishList);
            //跳转订单详情业
        	RequestDispatcher rDispatcher=request.getRequestDispatcher("./MerchantJSP/PersonalOrderStatementPage.jsp");
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
