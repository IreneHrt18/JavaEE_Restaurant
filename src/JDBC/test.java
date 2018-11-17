package JDBC;

import java.util.ArrayList;
import java.util.Iterator;

import Bean.Dish;
import Bean.Order;
import DAO.SearchDAO;
import DAOIMPL.DishIMPL;
import DAOIMPL.OrderIMPL;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Order> list = new ArrayList<>();
		SearchDAO searchDAO = new OrderIMPL();
		String[] params = {"100001"};
		list = (ArrayList<Order>)searchDAO.searchByPrimaryKey(params);
		//将dishlist转换成json数组
		
		Iterator<Order> i=list.iterator();
		while(i.hasNext()) {
			Order st=i.next();
			System.out.println(st.getORDERNO()+" "+st.getTIME());
			
		}
	}

}
