package DAOIMPL;

import java.util.ArrayList;

import Bean.Dish;
import Bean.Order;
import DAO.SearchDAO;
import JDBC.BaseDAO;
import JDBC.BaseDAOIMPL;

public class OrderIMPL implements SearchDAO {

	@Override
	public ArrayList searchByPrimaryKey(String[] params) {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = new BaseDAOIMPL();
		String sql = "select * from orders where orderno = ?";
		ArrayList list = baseDAO.searchOBJ(sql,params, Order.class);
		return list;
	}

	@Override
	public ArrayList searchAll() {
		BaseDAO baseDAO = new BaseDAOIMPL();
		String sql = "select * from orders";
		ArrayList list = baseDAO.searchOBJ(sql,null, Order.class);
		return list;
	}

}
