package DAO;

import Model.Dish;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DishDAOI {
    public boolean addDish(Dish dish) throws SQLException;
    public boolean delDish(int dishid) throws SQLException;
    public boolean alterDish(Dish dish) throws SQLException;
    public ArrayList<Dish> selDish(Dish dish) throws SQLException;
    public ArrayList<Dish> allDishes() throws SQLException;
}
