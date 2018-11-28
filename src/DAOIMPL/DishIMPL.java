package DAOIMPL;

import java.math.BigDecimal;
import java.util.ArrayList;

import Bean.Dish;
import DAO.DeleteDAO;
import DAO.InsertDAO;
import DAO.ModifyDAO;
import DAO.SearchDAO;
import DAO.SortDAO;
import JDBC.BaseDAO;
import JDBC.DAOFactory;

public class DishIMPL implements SearchDAO,SortDAO,ModifyDAO,DeleteDAO,InsertDAO {

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList searchAll() {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		String sql = "select * from dish ";

		ArrayList list = baseDAO.searchOBJ(sql,Dish.class);
		return list;
	}
	public ArrayList<Dish> sortAll(ArrayList<Dish> dishes,int order) {
		dishes.sort(new Dish.DishCompar(order));
		return dishes;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList searchByPrimaryKey(String[] params) {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		String sql = "select * from Dish where dishno = ?";
		ArrayList list = baseDAO.searchOBJ(sql,params, Dish.class);
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList searchByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select * from (select rownum as r,t1.* from dish t1 where rownum < ?) t2 where t2.r >= ?";
		BaseDAO basedao = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		Integer[] params = {currentPage*pageSize+1,(currentPage-1)*pageSize+1};
		ArrayList list = basedao.searchOBJ(sql, params, Dish.class);
		return list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from dish";
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		BigDecimal count =(BigDecimal)baseDAO.getCount(sql, null);
		return count.intValue();
	}

	@Override
	public int getCountByParam(String colunm, String value) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from dish where "+colunm+" = ?";
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		String[] params = {value};
		BigDecimal count = (BigDecimal)baseDAO.getCount(sql, params);
		return count.intValue();
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public ArrayList descSort(Object[] array) {
		// TODO Auto-generated method stub
		int[] nos = new int[array.length];
		SearchDAO searchDAO = (SearchDAO)DAOFactory.newInstance("DishOrder");
		Dish[] dishs = (Dish[])array;
		//获取dishno组成的数组
		String[] value = new String[array.length];
		for(int i = 0;i<value.length;i++) {
			value[i] = dishs[i].getDISHNO();
		}
		//获得所有含有dishno的订单数量
		nos = searchDAO.getMuiltCountByParams("dishno", value);
//		for(int i = 0;i<nos.length;i++) {
//			System.out.println(nos[i]);
//		}
//		System.out.println("**************");
		//快速排序
		quickSort(nos, 0, nos.length-1, (Dish[])array);
		
//		for(int i = 0;i<nos.length;i++) {
//			System.out.println(nos[i]);
//		}
//		System.out.println("**************");
		
		ArrayList<Dish> list = new ArrayList<>();
		for(int i = 0;i<array.length;i++) {
			list.add((Dish)array[i]);
		}
		return list;
	}
	/**
	 * 快排
	 * @param list
	 * @param a
	 * @param b
	 * @param dishs
	 */
	public void quickSort(int[] list,int a, int b,Dish[] dishs) {
		if(a>=b) return;
		int f = a ;
		int r = b ;
		int tmp = list[a];
		while(f<r) {
			while(list[r]<=tmp && f<r) r--;
			while(list[f]>=tmp && f<r) f++;
			if(f<r) {
				int t = list[f];
				list[f] = list[r];
				list[r] = t;
				
				Dish m = dishs[f];
				dishs[f] = dishs[r];
				dishs[r] = m;
			}
		}
		list[a] = list[f];
		list[f] = tmp;
		
		Dish m = dishs[a];
		dishs[a] = dishs[f];
		dishs[f] = m;

		quickSort(list, a, f-1,dishs);
		quickSort(list, f+1, b,dishs);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList fuzzyQuery(String colunm, String value) {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		String sql = "select * from dish where "+colunm+" like ?";
		return baseDAO.fuzzyQuery(sql, value, Dish.class);
	}

	@Override
	public int[] getMuiltCountByParams(String colunm, String[] value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyImgUrl(String[] params) {
		// TODO Auto-generated method stub
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		String sql = "update dish set img = ? where dishno = ?";
		return baseDAO.singleSQL(sql, params);
	}

	@Override
	public int modifyAllByPrimarykey(String primary, String[] params) {
		// TODO Auto-generated method stub
		String sql = "update dish set DESCRIPTION = ? ,DISHNAME = ? ,PRICE = ? where dishno = "+primary;
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		return baseDAO.singleSQL(sql, params);
	}
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
	public int[] deleteByPrimaryKey(String[] primaryKey) {
		// TODO 删除dish
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		String sql = "delete from dish  where dishno = ?";
		return baseDAO.multipleSQL(sql, primaryKey);
	}

	@Override
	public void insert(String[] params) {
		// TODO 插入记录
		String sql = " insert into dish(dishNo, dishName, price, description,img) "
				+ "values( ?,?,?,?,?)";
		BaseDAO baseDAO = (BaseDAO)DAOFactory.newInstance("BaseDAO");
		baseDAO.singleSQL(sql, params);
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
	public boolean addObj(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
