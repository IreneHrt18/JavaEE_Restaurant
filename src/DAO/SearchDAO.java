package DAO;

import java.util.ArrayList;

public interface SearchDAO {
	/**
	 * 通过主键查询
	 * @param params
	 * @return ArrayList
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList searchByPrimaryKey(String[] params);
	public ArrayList searchAll();
	/**
	 * 通过页面搜索
	 * @param currentPage
	 * @param pageSize
	 * @return ArrayList
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList searchByPage(int currentPage,int pageSize);
	/**
	 * 获得记录总数
	 * @param pageSize
	 * @return integer
	 */
	public int getCount();
	/**
	 * 通过列名和值查询记录数量,一条语句
	 * @param dishNo
	 * @return
	 */
	public int getCountByParam(String colunm,String value);
	/**
	 * 模糊查询
	 * @param colunm
	 * @param value
	 * @return arraylist
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList fuzzyQuery(String colunm,String value);
	/**
	 * 执行多条语句，参数只有一种，获得每条语句执行后的第一个值的数量
	 * @param colunm
	 * @param value
	 * @return
	 */
	public int[] getMuiltCountByParams(String colunm,String[] value);
}
