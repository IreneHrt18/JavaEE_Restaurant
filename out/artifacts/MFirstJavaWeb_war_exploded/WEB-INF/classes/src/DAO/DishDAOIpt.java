package DAO;

import Model.Dish;
import Servelet.OracleHelper;

import java.sql.*;
import java.util.ArrayList;

public class DishDAOIpt implements DishDAOI {

    @Override
    public boolean addDish(Dish dish) throws SQLException {
        Connection conn= OracleHelper.getConnection();
        PreparedStatement inserQuery=null;
        inserQuery=conn.prepareStatement("insert into dish values (?,?,?)");

        inserQuery.setInt(1,dish.getDishid());
        inserQuery.setString(2,dish.getName());
        inserQuery.setInt(3,dish.getPrice());
        inserQuery.executeQuery();

        OracleHelper.free(null,inserQuery,conn);

        return true;
    }

    @Override
    public boolean delDish(int dishid) throws SQLException {
        Connection conn= OracleHelper.getConnection();
        PreparedStatement delQuery=null;
        delQuery=conn.prepareStatement("delete  from dish where dishid = ?");
        delQuery.setInt(1,dishid);
        delQuery.executeQuery();
        OracleHelper.free(null,delQuery,conn);
        return true;
    }

    @Override
    public boolean alterDish(Dish dish) throws SQLException {
        Connection conn= OracleHelper.getConnection();
        PreparedStatement alterQuery=null;
        alterQuery=conn.prepareStatement("update dish set name = ?,price = ? where dishid =?");
        alterQuery.setString(1,dish.getName());
        alterQuery.setInt(2,dish.getPrice());
        alterQuery.setInt(3,dish .getDishid());
        alterQuery.executeQuery();

        OracleHelper.free(null,alterQuery,conn);
        return true;
    }

    @Override
    public ArrayList<Dish> selDish(Dish _dish) throws SQLException {
        ArrayList<Dish> dishes=new ArrayList<Dish>();


        //外部初始化new dish 会导致只有一个地址
//        Dish dish=new Dish();
//        Connection conn= OracleHelper.getConnection();
//        PreparedStatement seleQuery=null;
//        ResultSet resultSet=null;
//        seleQuery=conn.prepareStatement("select * from dish where name =? or DISHID =?");
//        seleQuery.setString(1,_dish.getName());
//        seleQuery.setLong(2,_dish.getDishid());
//        resultSet=seleQuery.executeQuery();
//        packingDish(dishes, resultSet);
//        OracleHelper.free(resultSet,seleQuery,conn);
        String sql="select * from dish where name =? or DISHID =?";
        Object para[]= {_dish.getName(), _dish.getDishid()};
        dishes=new BaseDAO().seleObj(sql,Dish.class,para);
        return dishes;
    }

    @Override
    public ArrayList<Dish> allDishes() throws SQLException {
        ArrayList<Dish> dishes=new ArrayList<Dish>();


        //外部初始化new dish 会导致只有一个地址
//        Dish dish=new Dish();
        Connection conn= OracleHelper.getConnection();
        PreparedStatement seleQuery=null;
        ResultSet resultSet=null;
        seleQuery=conn.prepareStatement("select * from dish ");
        resultSet=seleQuery.executeQuery();
        packingDish(dishes, resultSet);
        OracleHelper.free(resultSet,seleQuery,conn);
//        String sql="select dishid as dishID ,name as Name,age as Age,gender as Gender,birthday as Birthday,address as Address  from dish";
//        findObjs(sql,Dish.class);
        return dishes;
    }

    private void packingDish(ArrayList<Dish> dishes, ResultSet resultSet) throws SQLException {
        while (resultSet.next())
        {
            Dish dish=new Dish();
            dish.setName(resultSet.getString("name"));
            dish.setDishid(resultSet.getInt("dishid"));
            dish.setPrice((resultSet.getInt("price")));
            dishes.add(dish);
        }
    }
}
