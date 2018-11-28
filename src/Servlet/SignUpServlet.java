package Servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.User;
import DAO.SearchDAO;
import DAOIMPL.UserIMPL;
import JDBC.BaseDAO;
import JDBC.DAOFactory;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
    	String action =	req.getParameter("action");
    	if(action!=null) {
    		if(req.getParameter("action").equals("verify")) {
    			resp.getWriter().println(varify(req.getParameter("username")));
    		}
    	}else {
    		processSignup(req, resp);
    	}
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        doGet(req, resp);

    }
    
    private String varify(String username) {
		SearchDAO searchDAO = (SearchDAO)DAOFactory.newInstance("User");
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		String sql = "select count(*) from users where username = ?";
		String[] params = {username};
		BigDecimal value = (BigDecimal)baseDAO.getCount(sql, params);
		if(value.intValue()>=1) {
			return "用户名已存在";
		}
    	return "用户名未被注册";
	}
    
    private boolean processSignup(HttpServletRequest req, HttpServletResponse resp) {
        String username =req.getParameter("username");
        String password =req.getParameter("password");
        Date date =new Date();

        User user=new User();
        user.setUSERNO("0");
        user.setUSERNAME(username);
        user.setPASSWORD(password);

        if(new UserIMPL().addObj(user))
        {
            RequestDispatcher requestDispatcher=req.getRequestDispatcher("/UsersPage/LoginOrSignup.jsp");
            try {
                requestDispatcher.forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
        {
            RequestDispatcher requestDispatcher=req.getRequestDispatcher("/error");
            try {
                requestDispatcher.forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
