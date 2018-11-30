package Servlet;

import Bean.PageModel;
import Bean.User;
import DAO.SearchDAO;
import JDBC.BaseDAO;
import JDBC.DAOFactory;
import JDBC.Helper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(value = "/Login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processLogin(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processLogin(req, resp);

	}

	private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User tem_user = new User();
		if (checkAdmitor(username, password)) {
			tem_user.setUSERNAME(username);
			tem_user.setPASSWORD(password);
			tem_user.setUSERNO(username);
			req.getSession().setAttribute("user", tem_user);
			SearchDAO searchDAO = (SearchDAO) DAOFactory.newInstance("Dish");
			req.getSession().setAttribute("pageModel", new PageModel(searchDAO.getCount()));
			resp.sendRedirect("./MerchantJSP/DishManagementPage.jsp");
			return;
		} else if ((tem_user = chekIsUser(username, password)) != null) {
			HttpSession session = req.getSession(false);
			tem_user.setUSERNAME(username);
			tem_user.setPASSWORD(password);
			session.setAttribute("user", tem_user);
			session.setAttribute("failedLogin", " ");
			resp.sendRedirect("./CustomerJSP/GetMenues.jsp");
		} else {
			req.getSession(true).setAttribute("failedLogin", "用户名或密码错误，请重新输入！");
			ServletContext context = getServletContext();
			RequestDispatcher requestDispatcher = context.getRequestDispatcher("/LoginOrSignup.jsp");
			try {
				requestDispatcher.forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}

	}

	public static User chekIsUser(String _username, String password) {

		PreparedStatement selectQuery = null;
		ResultSet resultSet = null;

		Connection conn = Helper.getConnection();
		try {
			selectQuery = conn.prepareStatement("select PASSWORD,USERNO from users where username =?");
			selectQuery.setString(1, _username);
			resultSet = selectQuery.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("password").equals(password)) {
					User tem_user = new User();
					tem_user.setUSERNAME(_username);
					tem_user.setUSERNO(resultSet.getString("userno"));
					return tem_user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Helper.free(resultSet, selectQuery, conn);

		}
		return null;

	}

	public boolean checkAdmitor(String username, String password) {
		BaseDAO baseDAO = (BaseDAO) DAOFactory.newInstance("BaseDAO");
		String sql = "select count(*)  from users where username = ? and password = ?";
		String[] params = { username, password };
		BigDecimal value = (BigDecimal) baseDAO.getCount(sql, params);
		if (value.intValue() == 1&&username.equals("admin"))
			return true;
		return false;

	}

}