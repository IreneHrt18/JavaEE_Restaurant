package DAO;

import Model.Menu;
import Model.Restaurant;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MenuDAOIptTest {

    @Test
    public void addMenu() {
        ArrayList<String> list1=new ArrayList<String>();
        ArrayList<String> list2=new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        list2.add("c");
        list2.add("d");
        ArrayList<String> list3=new ArrayList<String>();
        list3.addAll(list1);
        list3.addAll(list2);

        for (String string :
                list3) {
            System.out.println(string);
        }

    }

    @Test
    public void delMenu() {
    }

    @Test
    public void alterMenu() {
    }

    @Test
    public void selMenu() {
        RestaurantDAOIpt restaurantDAOIpt=new RestaurantDAOIpt();
        MenuDAOIpt menuDAOIpt=new MenuDAOIpt();
        ArrayList<Menu> menus=new ArrayList<Menu>();
        Menu menuTeple=new Menu(new Restaurant(4),null);
        try {
            menus=menuDAOIpt.selMenu(menuTeple,MenuDAOIpt.BY_PRICE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Menu menu :
                menus) {
            System.out.println(menu.getDish().getName() + " " + menu.getDish().getPrice());
        }
    }

    @Test
    public void allMenu() {
    }
}