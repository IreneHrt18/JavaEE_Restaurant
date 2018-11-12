package Servelet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/error")
public class Error  extends HttpServlet
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
            throws IOException {

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>Login Result</title></head>");
        out.println("<body> Username or password is wrong,please reset it "  + "<br>");

        out.flush();
    }

    }
