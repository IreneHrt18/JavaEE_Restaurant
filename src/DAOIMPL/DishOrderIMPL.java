package DAOIMPL;

import java.math.BigDecimal;
import java.util.ArrayList;

import DAO.SearchDAO;
import JDBC.BaseDAO;
import JDBC.DAOFactory;

public class DishOrderIMPL implements SearchDAO {

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList searchByPrimaryKey(String[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList searchByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCountByParam(String colunm, String value) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from order_dish where "+colunm+" = ?";
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		String[] params= {value};
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
		String sql = "select count(*) from order_dish where "+colunm+" = ?";
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		Object[] rets = baseDAO.getMuiltCountByParams(sql, value);
		int[] num = new int[rets.length];
		for(int i = 0;i<num.length;i++) {
			num[i]= ((BigDecimal)rets[i]).intValue();
		}
		return num;
	}

}
