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
	 * һ�������ֲ���
	 * @param sql
	 * @param params
	 * @return
	 */
	public abstract int singleSQL(String sql,Object[] params);
	/**
	 * ֻ��һ�ֲ���ִ�ж��
	 * @param sql
	 * @param params
	 * @return
	 */
	public abstract int[] multipleSQL(String sql,Object[] params);
	/**
	 * ���ֲ���ִ�ж��
	 * arralist�д洢�˶��ֲ���������
	 * @param sql
	 * @param params
	 */
	public abstract int[] multipleParam(String sql,ArrayList<Object[]> params);
	/**
	 * ��ȡִ������Ľ������
	 * @param sql
	 * @param params
	 * @return
	 */
	public abstract int getCount(String sql,Object[] params);
	
	
	/**
	 * ģ����ѯ
	 * @param sql
	 * @param param
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public abstract ArrayList fuzzyQuery(String sql,String param,Class clazz);
}
