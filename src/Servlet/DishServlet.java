package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Dish;
import Bean.PageModel;
import DAO.SearchDAO;
import DAO.SortDAO;
import JDBC.DAOFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DishServlet
 */
@WebServlet("/DishServlet")
public class DishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String printString;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishServlet() {
        super();
        this.printString = null;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		switch (action) {
		case "search":
			printString = searchByDishNo(request.getParameter("searchText"),false);
			break;
		case "getPageModel":
			printString = getPageModel(request,response);
			break;
		case "searchByPage":
			printString = searchByPage(request, response,false);
			break;
		case "searchByPageAndSort":
			printString = searchByPage(request, response,true);
			break;
		case "searchAndSort":
			printString = searchByDishNo(request.getParameter("searchText"),true);
			break;
		default:
			break;
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(printString);
	}
	
	/**
	 * 通过页面查询
	 * @param request
	 * @param response
	 * @return string
	 */
	@SuppressWarnings("unchecked")
	public String searchByPage(HttpServletRequest request,HttpServletResponse response,boolean isSort) {
		SearchDAO searchDAO = (SearchDAO)DAOFactory.newInstance("Dish");
		//查询页面第一页的所有菜品信息
		int currenPage = Integer.parseInt(request.getParameter("currentPage"));
		ArrayList<Dish> list = (ArrayList<Dish>)searchDAO.searchByPage(currenPage, PageModel.getPageSize());
		if(isSort) {
			SortDAO sortDAO = (SortDAO)DAOFactory.newInstance("Dish");
			Dish[] dishs = (Dish[])list.toArray();
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
	 * 通过编号查询
	 * @param DishNo
	 * @return 一组json对象的字符串
	 */
	@SuppressWarnings("unchecked")
	private String searchByDishNo(String DishNo,boolean isSort) {
		//获取dishlist
		ArrayList<Dish> list = new ArrayList<>();
		SearchDAO searchDAO = (SearchDAO)DAOFactory.newInstance("Dish");
		if(DishNo!=null){
			list = (ArrayList<Dish>)searchDAO.fuzzyQuery("dishno", DishNo);
		}
		if(isSort) {
			SortDAO sortDAO = (SortDAO)DAOFactory.newInstance("Dish");
			Dish[] dishs = (Dish[])list.toArray();
			list.clear();
			list.addAll(sortDAO.descSort(dishs));
		}
		
		//将list转换成json对象
		JSONArray jsonArray = new JSONArray();
		for(int i =0;i<list.size();i++) {
			jsonArray.add(JSONObject.fromObject(list.get(i)));
		}
		return jsonArray.toString();
	}
	/**
	 * 获得pagemodel
	 * @param currentPage
	 * @param pageSize
	 * @return 一组json对象的字符串
	 */
	private String getPageModel(HttpServletRequest request,HttpServletResponse response) {
		SearchDAO searchDAO = (SearchDAO)DAOFactory.newInstance("Dish");
		//分页信息
		PageModel pageModel = new PageModel(searchDAO.getCount());
		JSONObject jsonObject = JSONObject.fromObject(pageModel);
		request.setAttribute("DishPageModel", pageModel);
		return jsonObject.toString();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
