package DAO;

import Model.Dish;
import Model.History;
import Model.Menu;
import Model.Restaurant;
import Servelet.OracleHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class HistoryDAOIpt extends BaseDAO implements HistoryDAOI {
    @Override
    public boolean addHistory(History history) {
        String sql="call insert_his(?,?,?)";
        Object para[]={history.getDishid(),
        history.getRestaurantid(),history.getBrowseDate()};
        modifyObj(sql,para);

        return true;
    }

    @Override
    public boolean delHistory(History history) {
        return false;
    }

    @Override
    public boolean alterHistory(History history) {
        return false;
    }

    @Override
    public ArrayList<History> selHistory() throws SQLException {
        ArrayList<History> histories=new ArrayList<History>();
//        String sql="select dishid,restaurantid,count(browse_date) as browse_date from history " +
//                "group by dishid,restaurantid order by count(browse_date)";
        PreparedStatement seleQuery=null;
        ResultSet resultSet=null;
        Connection conn= OracleHelper.getConnection();
        seleQuery=conn.prepareStatement("select dishid,restaurantid,count(browse_date) as browse_date from history " +
                    "group by dishid,restaurantid order by browse_date");
        resultSet=seleQuery.executeQuery();
        Restaurant restaurant =new Restaurant();
        Dish dish=new Dish();
        RestaurantDAOIpt restaurantDAOIpt=new RestaurantDAOIpt();
        DishDAOIpt dishDAOIpt=new DishDAOIpt();
        while (resultSet.next()){
            restaurant.setRestaurantid(resultSet.getInt("restaurantid"));
            dish.setDishid(resultSet.getInt("dishid"));
            Menu menu=new Menu(restaurantDAOIpt.selRestaurant(restaurant).get(0),
                    dishDAOIpt.selDish(dish).get(0)
                    );
            History history=new History(menu,new Date());
            histories.add(history);

        }

        OracleHelper.free(resultSet,seleQuery,conn);
        return histories;
    }
}
