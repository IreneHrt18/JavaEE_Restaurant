package DAO;

import Model.Dish;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DishDAOIptTest {

    @Test
    public void selDish() {
        try {
            ArrayList<Dish> dishes=new DishDAOIpt().selDish(new Dish(0,"油泼扯面",0,null,null));

            for(Dish dish :dishes)
            {
                System.out.println(dish.getName());
            System.out.println(dish.getPrice());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}