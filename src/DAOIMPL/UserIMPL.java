package DAOIMPL;


import java.util.ArrayList;

import Bean.User;
import DAO.InsertDAO;
import DAO.ModifyDAO;
import DAO.SearchDAO;
import JDBC.BaseDAO;
import JDBC.BaseDAOIMPL;

public class UserIMPL extends BaseDAOIMPL implements SearchDAO,ModifyDAO,InsertDAO {

	@Override
	public ArrayList searchByPrimaryKey(String[] params) {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = new BaseDAOIMPL();
		String sql = "select * from users where userNo  = ?";
		ArrayList list = baseDAO.searchOBJ(sql,params, User.class);
		return list;
	}

	@Override
	public ArrayList searchAll() {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = new BaseDAOIMPL();
		String sql = "select * from users";
		ArrayList list = baseDAO.searchOBJ(sql,null, User.class);
		return list;
	}

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
		return 0;
	}

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
	public int modifyImgUrl(String[] params) {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = new BaseDAOIMPL();
		String sql = "update users set iconURL = ? where userNo = ? ";
		return baseDAO.singleSQL(sql, params);
	}

	@Override
	public int modifyAllByPrimarykey(String primary, String[] params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList searchByParams(String[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList searchByPage(int currentPage, int pageSize, String viewName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(String viewName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean alterObj(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteObj(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insert(String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addObj(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
