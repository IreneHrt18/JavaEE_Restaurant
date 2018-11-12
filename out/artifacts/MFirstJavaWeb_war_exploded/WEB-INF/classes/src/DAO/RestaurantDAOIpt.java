package DAO;

import Model.Dish;
import Model.Restaurant;
import Servelet.OracleHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RestaurantDAOIpt implements RestaurantDAOI {
    @Override
    public boolean addRestaurant(Restaurant restaurant) throws SQLException {

        Connection conn= OracleHelper.getConnection();
        PreparedStatement inserQuery=null;
        inserQuery=conn.prepareStatement("insert into restaurant values (?,?,?,?)");

        inserQuery.setInt(1,restaurant.getRestaurantid());
        inserQuery.setString(2,restaurant.getName());
        inserQuery.setString(3,restaurant.getAddress());
        inserQuery.setString(4,restaurant.getPhone());
        inserQuery.executeQuery();

        OracleHelper.free(null,inserQuery,conn);
        return true;
    }

    @Override
    public boolean delRestaurant(int restaurantid) throws SQLException {

        Connection conn= OracleHelper.getConnection();
        PreparedStatement delQuery=null;
        delQuery=conn.prepareStatement("delete  from RESTAURANT where RESTAURANTID = ?");
        delQuery.setInt(1,restaurantid);
        delQuery.executeQuery();
        OracleHelper.free(null,delQuery,conn);
        return true;
    }

    @Override
    public ArrayList<Restaurant> selRestaurant(Restaurant _restaurant) throws SQLException {

        ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();

        Connection conn= OracleHelper.getConnection();
        PreparedStatement seleQuery=null;
        ResultSet resultSet=null;
        seleQuery=conn.prepareStatement("select * from RESTAURANT where RESTAURANTID =? or name =? or ADDRESS=?");
        seleQuery.setLong(1,_restaurant.getRestaurantid());
        //name即使为null也不影响查询
        seleQuery.setString(2,_restaurant.getName());
        seleQuery.setString(3,_restaurant.getAddress());
        resultSet=seleQuery.executeQuery();
        packingRestaurant(restaurants, resultSet);
        OracleHelper.free(resultSet,seleQuery,conn);
//        String sql="select dishid as dishID ,name as Name,age as Age,gender as Gender,birthday as Birthday,address as Address  from dish";
//        findObjs(sql,Dish.class);
        return restaurants;
    }

    @Override
    public ArrayList<Restaurant> allRestaurant() throws SQLException {

        ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
        PreparedStatement seleQuery=null;
        ResultSet resultSet=null;
        Connection conn= OracleHelper.getConnection();
        seleQuery=conn.prepareStatement("select * from RESTAURANT ");
        resultSet=seleQuery.executeQuery();
        packingRestaurant(restaurants, resultSet);

        OracleHelper.free(resultSet,seleQuery,conn);
        return restaurants;
    }

    private void packingRestaurant(ArrayList<Restaurant> restaurants, ResultSet resultSet) throws SQLException {
        while (resultSet.next())
        {
            Restaurant restaurant=new Restaurant();
            restaurant.setName(resultSet.getString("name"));
            restaurant.setRestaurantid(resultSet.getInt("restaurantid"));
            restaurant.setAddress((resultSet.getString("address")));
            restaurant.setPhone(resultSet.getString("phone"));
            restaurant.setFee(resultSet.getInt("fee"));
            restaurant.setNotic(resultSet.getString("notic"));
            restaurant.setStars(resultSet.getInt("stars"));
            restaurants.add(restaurant);
        }
    }

    @Override
    public boolean alterRestaurant(Restaurant restaurant) {
        return false;
    }
}
