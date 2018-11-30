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
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/CustomerJSP/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
        //获取当前的连接
        String currentURL = servletRequest.getRequestURI();
        //获取请求链接
        String targetURL = currentURL.substring(currentURL.indexOf("/",1),currentURL.length());
        //获得session对象
        HttpSession session = servletRequest.getSession(false);
        if(!"/LoginOrSignup.jsp".equals(targetURL)||!"/index.jsp".equals(targetURL)||!"/RegisterPage.jsp".equals(targetURL)){
            //如果不是跳转至登陆界面，并且不存在user
            if(session==null||session.getAttribute("user")==null){
                //将连接跳转至登陆界面
            	servletResponse.getWriter().println("<script>"
            			+ " alert('您还没有登陆,请先登陆吧~'); "
            			+" window.location.href='../index.jsp'; "
            			+ "</script>");
                return;
            }
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
