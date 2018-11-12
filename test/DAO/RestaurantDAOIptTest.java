package DAO;

import Model.Restaurant;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class RestaurantDAOIptTest {

    @Test
    public void selRestaurant() {
        RestaurantDAOI restaurantDAOI=new RestaurantDAOIpt();
        Restaurant restaurant=null;
        try {
             restaurant=restaurantDAOI.selRestaurant(new Restaurant(3)).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(restaurant.getName());
        System.out.println(restaurant.getFee());
        System.out.println(restaurant.getNotic());

    }
}