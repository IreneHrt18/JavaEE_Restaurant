package DAO;

import Model.Restaurant;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RestaurantDAOI {
    public boolean addRestaurant(Restaurant restaurant) throws SQLException;
    public boolean delRestaurant(int restaurantid) throws SQLException;
    public ArrayList<Restaurant> selRestaurant(Restaurant restaurant) throws SQLException;
    public ArrayList<Restaurant> allRestaurant() throws SQLException;
    public boolean alterRestaurant(Restaurant restaurant);
}
