package JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;

public abstract class BaseDAO {
	@SuppressWarnings("rawtypes")
	public abstract ArrayList searchOBJ(String sql,Class clazz);
	@SuppressWarnings("rawtypes")
	public abstract ArrayList searchOBJ(String sql , Object[] params, Class clazz);
	public abstract Object mapping(ResultSet rSet,Class<Object> clazz);
	/**
	 * 一个语句多种参数
	 * @param sql
	 * @param params
	 * @return
	 */
	public abstract int singleSQL(String sql,Object[] params);
	/**
	 * 只有一种参数执行多次
	 * @param sql
	 * @param params
	 * @return
	 */
	public abstract int[] multipleSQL(String sql,Object[] params);
	/**
	 * 多种参数执行多次
	 * arralist中存储了多种参数的数组
	 * @param sql
	 * @param params
	 */
	public abstract int[] multipleParam(String sql,ArrayList<Object[]> params);
	/**
	 * 获取执行语句后的结果数量
	 * @param sql
	 * @param params
	 * @return
	 */
	public abstract int getCount(String sql,Object[] params);
	
	
	/**
	 * 模糊查询
	 * @param sql
	 * @param param
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public abstract ArrayList fuzzyQuery(String sql,String param,Class clazz);
}
