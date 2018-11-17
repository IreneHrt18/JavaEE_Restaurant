package DAOIMPL;

import java.util.ArrayList;

import Bean.Dish;
import DAO.SearchDAO;
import JDBC.BaseDAO;
import JDBC.BaseDAOIMPL;

public class DishIMPL implements SearchDAO {

	@Override
	public ArrayList searchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList searchByPrimaryKey(String[] params) {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = new BaseDAOIMPL();
		String sql = "select * from Dish where dishno = ?";
		ArrayList list = baseDAO.searchOBJ(sql,params, Dish.class);
		return list;
	}

}
