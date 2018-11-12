package DAO;

import Model.Dish;
import Model.Menu;
import Model.Order;
import Model.Restaurant;
import Servelet.OracleHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOIpt extends BaseDAO implements OrderDAOI {
    @Override
    public boolean addOrder(Order order) {
        String sql="insert into order_list(dishid,restaurantid,amount,userid) values(?,?,?,?)";
        Object para[]={order.getMenu().getDish().getDishid(),
                order.getMenu().getRestaurant().getRestaurantid(),
                1,order.getUserid()
        };
        modifyObj(sql,para);
        return false;
    }

    @Override
    public boolean delOrder(Order order) {
        String sql="delete from order_list where restaurantid=? and dishid=? and userid=?";
        Object para[]={order.getMenu().getRestaurant().getRestaurantid(),
                order.getMenu().getDish().getDishid(),order.getUserid()};
        new BaseDAO().modifyObj(sql,para);
        return false;
    }

    @Override
    public boolean alterOrder(Order order) {
        String sql="update order_list set amount =? where userid=? and dishid =? and restaurantid=?";
        Object para[]={order.getAmount(),
                order.getUserid(),
                order.getMenu().getDish().getDishid(),
                order.getMenu().getRestaurant().getRestaurantid()};
        new BaseDAO().modifyObj(sql,para);
        return true;
    }

    @Override
    public Order selOrder(Order order) throws SQLException {

        ArrayList<Order> orders=new ArrayList<Order>();
        Connection conn= OracleHelper.getConnection();
        PreparedStatement seleQuery=null;
        ResultSet resultSet=null;
        String sql="select dishid,restaurantid,AMOUNT " +
                "from order_list where userid=? and RESTAURANTID=? and DISHID=? ";
        seleQuery=conn.prepareStatement(sql);
        seleQuery.setInt(1,order.getUserid());
        seleQuery.setInt(2,order.getMenu().getRestaurant().getRestaurantid());
        seleQuery.setInt(3,order.getMenu().getDish().getDishid());
        resultSet=seleQuery.executeQuery();


        packingOrder(order.getUserid(), orders, resultSet);
        if(orders.size()>0)
            return orders.get(0);
        else
            return null;
    }

    @Override
    public void trucOrderList() {
        String sql="TRUNCATE TABLE ORDER_LIST " +
                " PRESERVE MATERIALIZED VIEW " +
                "LOG REUSE STORAGE";

        new BaseDAO().modifyObj(sql,null);
    }

    private void packingOrder(int userid,  ArrayList<Order> orders, ResultSet resultSet) throws SQLException {
        RestaurantDAOIpt restaurantDAOIpt=new RestaurantDAOIpt();
        DishDAOIpt dishDAOIpt=new DishDAOIpt();
        Restaurant restaurant=new Restaurant();
        Dish dish =new Dish();
        while(resultSet.next())
        {
            restaurant.setRestaurantid(resultSet.getInt("restaurantid"));
            dish.setDishid(resultSet.getInt("dishid"));
            Menu menu=new Menu(
                    restaurantDAOIpt.selRestaurant(restaurant).get(0),
                    dishDAOIpt.selDish(dish).get(0)
            );

            Order order1=new Order(menu,resultSet.getInt("amount"),userid);
            orders.add(order1);
        }
    }

    @Override
    public ArrayList<Order> allOrders(Order order) throws SQLException {
        RestaurantDAOIpt restaurantDAOIpt=new RestaurantDAOIpt();
        DishDAOIpt dishDAOIpt=new DishDAOIpt();
        ArrayList<Order> orders=new ArrayList<Order>();
        Connection conn= OracleHelper.getConnection();
        PreparedStatement seleQuery=null;
        ResultSet resultSet=null;
        String sql="select dishid,restaurantid,AMOUNT " +
                "from order_list where userid=? order by RESTAURANTID";
        seleQuery=conn.prepareStatement(sql);
        seleQuery.setInt(1,order.getUserid());
        resultSet=seleQuery.executeQuery();
        Restaurant restaurant=new Restaurant();
        Dish dish =new Dish();

        packingOrder(order.getUserid(),  orders, resultSet);
        return orders;
    }
}
