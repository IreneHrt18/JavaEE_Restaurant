package DAOIMPL;

import java.util.ArrayList;

import Bean.Dish;
import DAO.SearchDAO;
import JDBC.BaseDAO;
import JDBC.BaseDAOIMPL;

public class DishIMPL implements SearchDAO {

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList searchByPrimaryKey(String[] params) {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = new BaseDAOIMPL();
		String sql = "select * from Dish where dishno = ?";
		ArrayList list = baseDAO.searchOBJ(sql,params, Dish.class);
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList searchByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select * from (select rownum as r,t1.* from dish t1 where rownum < ?) t2 where t2.r >= ?";
		BaseDAO basedao =new BaseDAOIMPL();
		Integer[] params = {currentPage*pageSize+1,(currentPage-1)*pageSize+1};
		ArrayList list = basedao.searchOBJ(sql, params, Dish.class);
		return list;
	}

}
