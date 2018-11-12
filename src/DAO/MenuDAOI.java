package DAO;

import Model.Menu;
import Model.Restaurant;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuDAOI {
    public boolean addMenu(Menu menu) throws SQLException;
    public boolean delMenu(Menu menu) throws SQLException;
    public boolean alterMenu(Menu menu);
    public ArrayList<Menu> selMenu(Menu menu,int order) throws SQLException;

    public ArrayList<Menu> searchMenu();
//    public ArrayList<Menu> allMenu() throws SQLException;

}
