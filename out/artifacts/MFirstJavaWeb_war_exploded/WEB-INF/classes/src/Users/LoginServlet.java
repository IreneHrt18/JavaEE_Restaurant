package Users;

import Model.User;
import Servelet.OracleHelper;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(value = "/Login")
public class LoginServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        processLogin(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        processLogin(req, resp);

    }

    private void processLogin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        String username=req.getParameter("username");
        String password=req.getParameter("password");

        User tem_user=null;
        if((tem_user=new OracleHelper().chekIsUser(username,password))!=null)
        {

            HttpSession session=req.getSession(true);
            session.setAttribute("userid",tem_user.getUserid());
            session.setAttribute("username",tem_user.getUsername());
            session.setAttribute("failedLogin"," ");
            ServletContext context=getServletContext();
            RequestDispatcher requestDispatcher=req.getRequestDispatcher("RestaurantPage.jsp");

            try {
                requestDispatcher.forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
            //context.setInitParameter("userid", String.valueOf(tem_user.getUserid()));

        }
        else
        {
            req.getSession(true).setAttribute("failedLogin","用户名或密码错误，请重新输入！");
            ServletContext context=getServletContext();
            RequestDispatcher requestDispatcher=context.getRequestDispatcher("/UsersPage/LoginOrSignup.jsp");
            try {
                requestDispatcher.forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }


    }

}