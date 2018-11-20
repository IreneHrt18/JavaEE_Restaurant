package DAO;

import java.util.ArrayList;

public interface SortDAO {
	/**
	 * 降序排列
	 * @param list
	 * @return arraylist
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList descSort(Object[] array);
}
