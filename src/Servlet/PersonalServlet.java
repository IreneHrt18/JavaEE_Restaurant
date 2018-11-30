package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import Bean.User;
import DAO.SearchDAO;
import DAOIMPL.UserIMPL;
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

    private String searchByUserNo(String UserNo) {
		//ͨ���ӿڻ�ò�ѯ���dishlist
		ArrayList<User> list = new ArrayList<>();
		SearchDAO searchDAO = new UserIMPL();
		String[] params = {UserNo};
		list = (ArrayList<User>)searchDAO.searchByPrimaryKey(params);
		//��dishlistת����json����
		JSONArray jsonArray = new JSONArray();
		for(int i =0;i<list.size();i++) {
			jsonArray.add(JSONObject.fromObject(list.get(i)));
		}
		
		return jsonArray.toString();
	}
    
  //statement
  	private User searchUser(String UserNo){
  		ArrayList<User> list = new ArrayList<>();
  		SearchDAO searchDAO = new UserIMPL();
  		String[] params = {UserNo};
  		list = (ArrayList<User>)searchDAO.searchByPrimaryKey(params);
  		User user=list.get(0);
  		return user;
  	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");
		String usernumber = null;
		switch (action) {
		case "search":
			String UserNo = request.getParameter("searchText");
			response.getWriter().println(searchByUserNo(UserNo));			
			break;
		case "statement":
			//��ȡ��ѯ������
			usernumber=request.getParameter("usernumber");
			User user=searchUser(usernumber);
            request.setAttribute("user", user);
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
