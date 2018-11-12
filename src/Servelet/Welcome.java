package Servelet;


import DAO.DishDAOIpt;
import DAO.RestaurantDAOIpt;
import Model.Dish;
import Model.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/welcome")
public class Welcome extends HttpServlet {
    static final String DB_USER="system";
    static final String DB_PWD="ting0818";
    static final String ORCL_URL="jdbc:oracle:thin:@localhost:1521/Myorcl";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processLogin(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processLogin(req, resp);

    }
    private void processLogin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=GBK");

        PrintWriter out =resp.getWriter();
        ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
        ArrayList<Dish> dishes=new ArrayList<Dish>();
//        try {
//            //restaurant=new RestaurantDAOIpt().allRestaurant();
//            dishes=new DishDAOIpt().allDishes();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        for(int i=0;i<restaurant.size();i++)
//        {
//            //Restaurant restaurant1=restaurant.get(i);
//            Dish dish=dishes.get(i);
//
//            out.println(dish.getName());
//        }
//        out.println("<h1> welcome </h1>");

        int restaurantid= Integer.parseInt(req.getParameter("restid"));
        Restaurant restaurant1=new Restaurant(restaurantid);
        try {
            restaurants=new RestaurantDAOIpt().selRestaurant(restaurant1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Restaurant restaurant:
             restaurants) {

            out.println(restaurant.getName());
        }
        out.flush();
    }


}