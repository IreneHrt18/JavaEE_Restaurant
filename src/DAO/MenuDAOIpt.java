package DAO;

import Model.Dish;
import Model.Menu;
import Model.Restaurant;
import Servelet.OracleHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDAOIpt implements MenuDAOI {
    public static  final int BY_NAME=1;
    public static final int BY_PRICE=2;
    public static final int BY_NAME_DESC=3;
    public static final int BY_PRICE_DESC=0;
    @Override
    public boolean addMenu(Menu menu) throws SQLException {

        Connection conn= OracleHelper.getConnection();
        PreparedStatement inserQuery=null;
        inserQuery=conn.prepareStatement("insert into MENU values (?,?)");

        inserQuery.setInt(1,menu.getDish().getDishid());
        inserQuery.setInt(2,menu.getRestaurant().getRestaurantid());
        inserQuery.executeQuery();

        OracleHelper.free(null,inserQuery,conn);
        return true;
    }

    @Override
    public boolean delMenu(Menu menu) throws SQLException {

        Connection conn= OracleHelper.getConnection();
        PreparedStatement delQuery=null;
        delQuery=conn.prepareStatement("delete  from MENU where DISHID=? and RESTAURANTID = ?");
        delQuery.setInt(1,menu.getDish().getDishid());
        delQuery.setInt(2,menu.getRestaurant().getRestaurantid());
        delQuery.executeQuery();
        OracleHelper.free(null,delQuery,conn);
        return true;
    }

    @Override
    public boolean alterMenu(Menu menu) {

        return true;
    }

    @Override
    public ArrayList<Menu> searchMenu() {
        return null;
    }

    //只会列出每个餐厅所有菜单，也就是只根据餐厅查找菜单
    @Override
    public ArrayList<Menu> selMenu(Menu menu,int order) throws SQLException {
        ArrayList<Menu> menus=new ArrayList<Menu>();

        PreparedStatement seleQuery=null;
        ResultSet resultSet=null;

        Connection conn= OracleHelper.getConnection();
        String sql="select DISH_ID, DISH_NAME,PRICE,IMG,DES,REST_NAME,ADDRESS,PHONE,FEE,STARS,NOTIC from ALL_MENUES where REST_ID=? or DISH_ID=? order by ";
        switch (order)
        {
            case BY_NAME:
                sql+="dish_name";
                break;
            case BY_PRICE:
                sql+="price";
                break;
            case BY_PRICE_DESC:
                sql+="price desc ";
                break;
                default:
                    sql+="dish_name desc";

        }
        seleQuery=conn.prepareStatement(sql
//                "select DISHID,RESTAURANTID from menu where DISHID=? or RESTAURANTID=?"
        );
        seleQuery.setInt(1,menu.getRestaurant().getRestaurantid());
        seleQuery.setInt(2,menu.getDish()==null?0:menu.getDish().getDishid());

                resultSet=seleQuery.executeQuery();
        packingMenu(menus, resultSet);
        OracleHelper.free(resultSet,seleQuery,conn);
//        String sql="select dishid as dishID ,name as Name,age as Age,gender as Gender,birthday as Birthday,address as Address  from dish";
//        findObjs(sql,Dish.class);
        return menus;
    }

//    @Override
//    public ArrayList<Menu> allMenu() throws SQLException {
//        ArrayList<Menu> menues=new ArrayList<Menu>();
//
//        Connection conn= OracleHelper.getConnection();
//        PreparedStatement seleQuery=null;
//        ResultSet resultSet=null;
//        seleQuery=conn.prepareStatement("select * from Menu ");
//        resultSet=seleQuery.executeQuery();
//        packingMenu(menues, resultSet);
//        OracleHelper.free(resultSet,seleQuery,conn);
////        String sql="select dishid as dishID ,name as Name,age as Age,gender as Gender,birthday as Birthday,address as Address  from dish";
////        findObjs(sql,Dish.class);
//        return menues;
//    }

    private void packingMenu(ArrayList<Menu> menues, ResultSet resultSet) throws SQLException {
        while (resultSet.next())
        {
            Menu menu0=new Menu();
            Dish dish =new Dish(resultSet.getInt("dish_id"),
                    resultSet.getString("dish_name"),
                    resultSet.getInt("price"),
                    resultSet.getString("img"),
                    resultSet.getString("des")
                    );
            Restaurant restaurant=new Restaurant(0,
                    resultSet.getString("rest_name"),
                    resultSet.getString("address"),
                    resultSet.getString("phone"),
                    resultSet.getString("notic"),
                    resultSet.getInt("fee"),
                    resultSet.getInt("stars")
                    );

            menu0.setDish(dish);
            menu0.setRestaurant(restaurant);

            menues.add(menu0);
        }
    }
}
