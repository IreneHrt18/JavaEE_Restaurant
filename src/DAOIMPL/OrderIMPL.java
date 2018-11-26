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
		String sql = "select * from view_Order where orderno = ?";
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
		String sql = "select t2.orderNo,t2.userNo,t1.userName,t2.price,t2.time,t2.orderState,t2.commentState\r\n" + 
				"from users t1,orders t2\r\n" + 
				"where t1.userNo=t2.userNo";
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
		String sql = "select * from (select rownum as r,t1.* from view_Order t1 where rownum < ?) t2 where t2.r >= ?";
		BaseDAO basedao = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		Integer[] params = {currentPage*pageSize+1,(currentPage-1)*pageSize+1};
		ArrayList list = basedao.searchOBJ(sql, params, Order.class);
		return list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from view_Order";
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		BigDecimal count =(BigDecimal)baseDAO.getCount(sql, null);
		return count.intValue();
	}

	@Override
	public int getCountByParam(String colunm, String value) {
		String sql = "select count(*) from view_Order where "+colunm+" = ?";
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
	@Override
	public ArrayList searchByPage(int currentPage, int pageSize, String viewName) {
		// TODO Auto-generated method stub
		String sql = "select * from (select rownum as r,t1.* from view_Order t1 where rownum < ?) t2 where t2.r >= ? AND userNo="+viewName;
		BaseDAO basedao = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		Integer[] params = {currentPage*pageSize+1,(currentPage-1)*pageSize+1};
		ArrayList list = basedao.searchOBJ(sql, params, Order.class);
		return list;
	}
	@Override
	public int getCount(String viewName) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from view_Order where userNO="+viewName;
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		BigDecimal count =(BigDecimal)baseDAO.getCount(sql, null);
		return count.intValue();
	}
	@Override
	public ArrayList searchByParams(String[] params) {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = new BaseDAOIMPL();
		String sql = "select * from view_Order where userNo=? AND orderno = ?";
		ArrayList list = baseDAO.searchOBJ(sql,params, Order.class);
		return list;
	}
}
