package JDBC;

import java.util.ArrayList;

import Bean.Dish;
import Bean.PageModel;
import DAO.SearchDAO;
import DAO.SortDAO;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SearchDAO searchDAO = (SearchDAO)DAOFactory.newInstance("Dish");
		ArrayList<Dish> list = (ArrayList<Dish>)searchDAO.searchByPage(1, PageModel.getPageSize());
		if(true) {
			SortDAO sortDAO = (SortDAO)DAOFactory.newInstance("Dish");
			Dish[] dishs = new Dish[list.size()];
			list.toArray(dishs);
			for(int i = 0;i<dishs.length;i++) {
				System.out.println(dishs[i].toString());
			}
			System.out.println("*****************");
			list.clear();
			list.addAll(sortDAO.descSort(dishs));
			for(int i = 0;i<list.size();i++) {
				System.out.println(list.get(i).toString());
			}
		}
	}

}
