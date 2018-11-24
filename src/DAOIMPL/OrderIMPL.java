package DAOIMPL;

import java.math.BigDecimal;
import java.util.ArrayList;

import Bean.Dish;
import Bean.Order;
import DAO.SearchDAO;
import JDBC.BaseDAO;
import JDBC.BaseDAOIMPL;
import JDBC.DAOFactory;

public class OrderIMPL implements SearchDAO {
	@SuppressWarnings("rawtypes")
	@Override
	/**
	 * 主键查询
	 */
	public ArrayList searchByPrimaryKey(String[] params) {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = new BaseDAOIMPL();
		String sql = "select * from orders where orderno = ?";
		ArrayList list = baseDAO.searchOBJ(sql,params, Order.class);
		return list;
	}
	@SuppressWarnings("rawtypes")
	@Override
	/**
	 * 查询全部订单信息
	 */
	public ArrayList searchAll() {
		BaseDAO basedao = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		String sql = "select * from orders";
		ArrayList list = basedao.searchOBJ(sql,null, Order.class);
		return list;
	}
	/**
	 * 查询订单中菜品信息
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
    public ArrayList<Dish> searchDishOfOrder(String[] params){
		BaseDAO baseDAO = new BaseDAOIMPL();
		String sql = "select * from dish where dishNo in(select dishNO from order_dish where orderNo=?)";
		ArrayList list = baseDAO.searchOBJ(sql,params, Dish.class);
		return list;	
    }
    @SuppressWarnings("rawtypes")
	@Override
	public ArrayList searchByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select * from (select rownum as r,t1.* from orders t1 where rownum < ?) t2 where t2.r >= ?";
		BaseDAO basedao = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		Integer[] params = {currentPage*pageSize+1,(currentPage-1)*pageSize+1};
		ArrayList list = basedao.searchOBJ(sql, params, Order.class);
		return list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from orders";
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		BigDecimal count =(BigDecimal)baseDAO.getCount(sql, null);
		return count.intValue();
	}

	@Override
	public int getCountByParam(String colunm, String value) {
		String sql = "select count(*) from orders where "+colunm+" = ?";
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		String[] params = {value};
		BigDecimal count = (BigDecimal)baseDAO.getCount(sql, params);
		return count.intValue();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList fuzzyQuery(String colunm, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getMuiltCountByParams(String colunm, String[] value) {
		// TODO Auto-generated method stub
		return null;
	}
}
