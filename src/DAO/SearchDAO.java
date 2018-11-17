package DAO;

import java.util.ArrayList;

public interface SearchDAO {
	/**
	 * 通过主键查询
	 * @param params
	 * @return 返回arralist对象 可能为空
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList searchByPrimaryKey(String[] params);
	/**
	 * 查找当前页的元素
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList searchByPage(int currentPage,int pageSize);
}
