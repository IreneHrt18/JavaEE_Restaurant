package DAO;

import Model.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAOI {
    public boolean addOrder(Order order);
    public boolean delOrder(Order order);
    public boolean alterOrder(Order order);
    public Order selOrder(Order order) throws SQLException;
    public void trucOrderList();
    public ArrayList<Order> allOrders(Order order) throws SQLException;
}
