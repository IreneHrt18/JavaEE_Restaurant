package DAOIMPL;

import java.util.ArrayList;

import Bean.Dish;
import Bean.Dish_Order;
import Bean.Order;
import DAO.InsertDAO;
import DAO.SearchDAO;
import JDBC.BaseDAO;
import JDBC.BaseDAOIMPL;

public class OrderIMPL extends BaseDAOIMPL implements SearchDAO, InsertDAO {

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
    public ArrayList<Dish> searchDishOfOrder(String[] params){
		BaseDAO baseDAO = new BaseDAOIMPL();
		String sql = "select * from dish where dishNo in(select dishNO from order_dish where orderNo=?)";
		ArrayList list = baseDAO.searchOBJ(sql,params, Dish.class);
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
	public boolean addObj(Object obj) {
		Order order =(Order) obj;
		String sql ="insert into orders(orderno,userno,price,time,commentstate,orderstate)" +
				" values(?,?,?,?,?,?)";
		Object[] param={order.getORDERNO(),order.getUSERNO(),order.getPRICE(),
				order.getTIME(),order.getCOMMENTSTATE(),order.getORDERSTATE()};
		modifyObj(sql,param);
		for (Dish_Order dish:
			 order.getDishes()) {
			String subSql="insert into order_dish(orderno,dishno,comments,dishcount) values(?,?,?,?)";
			Object []subParam={dish.getOrderno(),dish.getDishno(),dish.getComments(),dish.getDishcount()};
			modifyObj(subSql,subParam);
		}

		return true;
	}
}
