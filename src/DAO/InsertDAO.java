package DAO;

public interface InsertDAO {
	/**
	 * 插入记录注意元素排序
	 * @param params
	 */
	public void insert(String[] params);
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public boolean addObj(Object obj );
}
