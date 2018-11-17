package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String editAmount=req.getParameter("editAmount");
        if(req.getParameter("trunc")!=null)
            trunc(req, resp);
        else if(editAmount==null && req.getParameter("reedit")==null)
            addToCart(req,resp);
        else
            modifyCart(req,resp);


    }

    private void trunc(HttpServletRequest req, HttpServletResponse resp) {
        new OrderDAOIpt().trucOrderList();
        toCartPage(req, resp);
    }

    private void toCartPage(HttpServletRequest req, HttpServletResponse resp) {
        try {

            RequestDispatcher requestDispatcher=req.getRequestDispatcher("CartPage.jsp");
            requestDispatcher.forward(req,resp);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        addToCart(req, resp);

    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) {

        int dishid= Integer.parseInt(req.getParameter("dishid"));
        int restid= Integer.parseInt(req.getParameter("restid"));

        Restaurant restaurant=new Restaurant(restid);
        Dish dish=new Dish(dishid);
        Menu menu=new Menu(restaurant,dish);
        History history=new History(menu.getDish().getDishid(),menu.getRestaurant().getRestaurantid(),new Date());

        //Order order=new Order(menu,1,Integer.parseInt(getServletContext().getInitParameter("userid")));
        Order order=new Order(menu,1, (Integer)req.getSession(false).getAttribute("userid"));

        OrderDAOI orderDAOI=new OrderDAOIpt();
        boolean hasOrder=false;
        int pasAmount=0;
        Order returnOrder=null;
        try {
            returnOrder=orderDAOI.selOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }

            hasOrder=returnOrder!=null?true:false;

        if(!hasOrder){
            new HistoryDAOIpt().addHistory(history);
            orderDAOI.addOrder(order);
        }else
        {
            order.setAmount(returnOrder.getAmount()+1);
            orderDAOI.alterOrder(order);
        }

        try {
                RequestDispatcher requestDispatcher=req.getRequestDispatcher("GetMenues.jsp");
                //如何舍弃url中原有的“CartServlet.jsp”
                requestDispatcher.forward(req,resp);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modifyCart(HttpServletRequest req, HttpServletResponse resp)
    {
        OrderDAOI orderDAOI=new OrderDAOIpt();
        int dishid= Integer.parseInt(req.getParameter("dishid"));
        int restid= Integer.parseInt(req.getParameter("restid"));
        String editAmount=req.getParameter("editAmount");
        int amount=Integer.parseInt(editAmount);
        //new OrderDAOIpt().addOrder();
        Restaurant restaurant=new Restaurant(restid);
        Dish dish=new Dish(dishid);
        Menu menu=new Menu(restaurant,dish);

        //Order order=new Order(menu,1,Integer.parseInt(getServletContext().getInitParameter("userid")));
        Order order=new Order(menu,amount, (Integer)req.getSession().getAttribute("userid"));

        if(amount>0)
            orderDAOI.alterOrder(order);
        else
            orderDAOI.delOrder(order);


        toCartPage(req, resp);
    }

}
