package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import Bean.Dish;
import Bean.PageModel;
import DAO.ModifyDAO;
import DAO.SearchDAO;
import DAO.SortDAO;
import JDBC.DAOFactory;
import JDBC.Helper;
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
		response.setCharacterEncoding("utf-8");
		//如果action为空就判断客户端要上传文件，节省时间
		if(action!=null) {
			switch (action) {
			case "search":
				printString = searchByDishNo(request.getParameter("searchText"),false);
				response.getWriter().println(printString);
				return;
			case "getPageModel":
				printString = getPageModel(request,response);
				response.getWriter().println(printString);
				return;
			case "searchByPage":
				printString = searchByPage(request, response,false);
				response.getWriter().println(printString);
				return;
			case "searchByPageAndSort":
				printString = searchByPage(request, response,true);
				response.getWriter().println(printString);
				return;
			case "searchAndSort":
				printString = searchByDishNo(request.getParameter("searchText"),true);
				response.getWriter().println(printString);
				return;
			case "jumpToDetail":
				jumpToDetail(request, response);
				return;
			case "submitModify":
				submitModify(request, response);
				return;
			}	
		}else if(action == null && request.getSession().getAttribute("currentDish")!=null) {
			uploadImg(request, response);
		}
	}

	/**
	 * 通过页面查询
	 * @param request
	 * @param response
	 * @return string
	 */
	@SuppressWarnings("unchecked")
	private String searchByPage(HttpServletRequest request,HttpServletResponse response,boolean isSort) {
		SearchDAO searchDAO = (SearchDAO)DAOFactory.newInstance("Dish");
		//查询页面第一页的所有菜品信息
		int currenPage = Integer.parseInt(request.getParameter("currentPage"));
		ArrayList<Dish> list = (ArrayList<Dish>)searchDAO.searchByPage(currenPage, PageModel.getPageSize());
		if(isSort) {
			SortDAO sortDAO = (SortDAO)DAOFactory.newInstance("Dish");
			Dish[] dishs = list.toArray(new Dish[list.size()]);
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
			Dish[] dishs = list.toArray(new Dish[list.size()]);
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
	 * 上传文件
	 * @param request
	 * @param response
	 */
	private void uploadImg(HttpServletRequest request,HttpServletResponse response) {
		if(ServletFileUpload.isMultipartContent(request)) {
			try {
				// 1.
			    //创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// System.out.println(System.getProperty("java.io.tmpdir"));//默认临时文件夹
			    // 2
				//. 创建ServletFileUpload对象，并设置上传文件的大小限制。
				ServletFileUpload sfu = new ServletFileUpload(factory);
				sfu.setSizeMax(10 * 1024 * 1024);// 以byte为单位 不能超过10M 1024byte =1kb 1024kb=1M 1024M = 1G
				sfu.setHeaderEncoding("utf-8");
				// 3.
				// 调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
				List<FileItem> fileItemList = sfu.parseRequest(new ServletRequestContext(request));
				Iterator<FileItem> fileItems = fileItemList.iterator();
				// 4. 遍历list，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件
				while (fileItems.hasNext()) {
				    FileItem fileItem = fileItems.next();
				    if (!fileItem.isFormField()) { 
				    // 文件后缀
					String suffix = fileItem.getName().substring(fileItem.getName().lastIndexOf('.'));
					// 在session中动态获取菜品编号作为文件名
					String newFileName = (String)request.getSession().getAttribute("currentDish")+"Dish"+ suffix;
					
					//读取文件中的路径配置
					Properties properties = new Properties();
					InputStream iStream = Helper.class.getResourceAsStream("./dbconfig.property");
					properties.load(iStream);
					// 5. 调用FileItem的write()方法，写入文件，上传文件到指定服务器地址
					File file = new File(properties.getProperty("img") + newFileName);
					fileItem.write(file);
					// 6. 调用FileItem的delete()方法，删除临时文件
					fileItem.delete();
					//修改数库中的菜品对应url
					ModifyDAO modifyDAO = (ModifyDAO)DAOFactory.newInstance("Dish");
					String[] params= {properties.getProperty("img") + newFileName,(String)request.getSession().getAttribute("currentDish")};
					modifyDAO.modifyImgUrl(params);
					request.getSession().removeAttribute("currentDish");
					}
				}
		}catch (FileUploadException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	/**
	 * 跳转到详情页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	private void jumpToDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SearchDAO searchDAO = (SearchDAO)DAOFactory.newInstance("Dish");
		ArrayList<Dish> list = searchDAO.searchByPrimaryKey(request.getParameterValues("dishNo"));
		request.getSession().setAttribute("dishList", list);
		response.sendRedirect("./MerchantJSP/DishStatement.jsp");
	}
	/**
	 * 提交详情页的修改
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void submitModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] params = {request.getParameter("description"),request.getParameter("dishName"),request.getParameter("price")};
		ModifyDAO modifyDAO = (ModifyDAO)DAOFactory.newInstance("Dish");
		modifyDAO.modifyAllByPrimarykey(request.getParameter("dishNo"), params);
		jumpToDetail(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
