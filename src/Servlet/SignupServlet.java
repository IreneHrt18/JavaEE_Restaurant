package Servlet;



import Bean.User;
import DAOIMPL.UserIMPL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        processSignup(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        processSignup(req, resp);

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
