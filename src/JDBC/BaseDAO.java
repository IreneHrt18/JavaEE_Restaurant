package JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;

public abstract class BaseDAO {
	/**
	 * 查询不带参数
	 * @param sql
	 * @param clazz
	 * @return ArrayList
	 */
	@SuppressWarnings("rawtypes")
	public abstract ArrayList searchOBJ(String sql,Class clazz);
	@SuppressWarnings("rawtypes")
	/**
	 * 查询带参数
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 */
	public abstract ArrayList searchOBJ(String sql , Object[] params, Class clazz);
	/**
	 * 部署
	 * @param rSet
	 * @param clazz
	 * @return Object
	 */
	public abstract Object mapping(ResultSet rSet,Class<Object> clazz);
	/**
	 * 执行单个语句，多个参数
	 * @param sql
	 * @param params
	 * @return integer
	 */
	public abstract int singleSQL(String sql,Object[] params);
	/**
	 * ֻ执行多个语句，同种参数
	 * @param sql
	 * @param params
	 * @return integer[]
	 */
	public abstract int[] multipleSQL(String sql,Object[] params);
	/**
	 * 获得执行语句后的第一个值
	 * @param sql
	 * @param params
	 * @return Object
	 */
	public abstract Object getCount(String sql,Object[] params);
	
	
	/**
	 * 模糊查询
	 * @param sql
	 * @param param
	 * @param clazz
	 * @return arralist
	 */
	@SuppressWarnings("rawtypes")
	public abstract ArrayList fuzzyQuery(String sql,String param,Class clazz);
	/**
	 * 执行多条语句，参数只有一种，获得每条语句执行后的第一个值的数量
	 * @param colunm
	 * @param value
	 * @return
	 */
	public abstract Object[] getMuiltCountByParams(String sql,Object[] value);
}
