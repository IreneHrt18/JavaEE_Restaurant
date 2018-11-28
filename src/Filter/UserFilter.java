package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.User;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/MerchantJSP/*")
public class UserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        if(servletRequest.getSession(false)!=null&&servletRequest.getSession(false).getAttribute("user")!=null) {
        	User user =	(User)servletRequest.getSession(false).getAttribute("user");
        	if(!user.getUSERNAME().equals("admin")) {
        		servletResponse.getWriter().println("<script>"
            			+ " alert('您还没有登陆,请先登陆吧~'); "
            			+" window.location.href='../index.jsp'; "
            			+ "</script>");
        		return;
        	}
        }else {
        	servletResponse.sendRedirect("../index.jsp");
        	return;
        }
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
