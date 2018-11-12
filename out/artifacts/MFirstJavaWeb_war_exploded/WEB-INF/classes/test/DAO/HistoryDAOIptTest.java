package DAO;

import Model.Dish;
import Model.History;
import Model.Menu;
import Model.Restaurant;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class HistoryDAOIptTest {

    @Test
    public void addHistory() {
        HistoryDAOIpt historyDAOIpt=new HistoryDAOIpt();
        historyDAOIpt.addHistory(new History
                (3,80,new Date()));

    }

    @Test
    public void delHistory() {
    }

    @Test
    public void alterHistory() {
    }

    @Test
    public void selHistory() {
        ArrayList<History> histories= null;
        try {
            histories = new HistoryDAOIpt().selHistory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        for (History history :
//                histories) {
//            try {
//                System.out.println(
//                        new RestaurantDAOIpt().selRestaurant(new Restaurant(history.getRestaurantid())).get(0).getName()+" "+
//                        history.);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Test
    public void addHistory1() {
    }
}