package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Dish;
import DAO.SearchDAO;
import DAOIMPL.DishIMPL;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DishServlet
 */
@WebServlet("/DishServlet")
public class DishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishServlet() {
        super();
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
			String DishNo = request.getParameter("searchText");
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(searchByDishNo(DishNo));
			break;
		default:
			break;
		}
	}
	/**
	 * 
	 * @param DishNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String searchByDishNo(String DishNo) {
		//通过接口获得查询后的dishlist
		ArrayList<Dish> list = new ArrayList<>();
		SearchDAO searchDAO = new DishIMPL();
		if(DishNo!=null){
			String[] params = {DishNo};
			list = (ArrayList<Dish>)searchDAO.searchByPrimaryKey(params);
		}else{
			//list = (ArrayList<Dish>)searchDAO.searchByPage(currentPage, pageSize);
		}
		//将dishlist转换成json数组
		JSONArray jsonArray = new JSONArray();
		for(int i =0;i<list.size();i++) {
			jsonArray.add(JSONObject.fromObject(list.get(i)));
		}
		return jsonArray.toString();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
