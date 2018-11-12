package DAO;

import Model.Dish;
import Model.Menu;
import Model.Order;
import Model.Restaurant;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOIptTest {

    @Test
    public void addOrder() {
        Menu menu=new Menu(new Restaurant(3),new Dish(69));
        Order order=new Order(menu,1,0);
        new OrderDAOIpt().addOrder(order);
    }

    @Test
    public void delOrder() {
    }

    @Test
    public void alterOrder() {
    }

    @Test
    public void selOrder() {
        OrderDAOIpt orderDAOIpt=new OrderDAOIpt();
        ArrayList<Order> orders=new ArrayList<Order>();
        int totalPrice=0;
        Order _order=new Order(null,0,
                2);
        try {
            orders=orderDAOIpt.allOrders(_order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}