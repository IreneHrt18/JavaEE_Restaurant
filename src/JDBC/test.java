package JDBC;

import java.util.ArrayList;

import Bean.Dish;
import DAO.SearchDAO;
import DAOIMPL.DishIMPL;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ͨ���ӿڻ�ò�ѯ���dishlist
		ArrayList<Dish> list = new ArrayList<>();
		SearchDAO searchDAO = new DishIMPL();
		String[] params = { "2" };
		list = (ArrayList<Dish>) searchDAO.searchByPrimaryKey(params);
		// ��dishlistת����json����
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			jsonArray.add(JSONObject.fromObject(list.get(i)));
		}

	}

}