package JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;

public abstract class BaseDAO {
	/**
	 * ���������Ĳ�ѯ
	 * @param sql
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public abstract ArrayList searchOBJ(String sql,Class clazz);
	@SuppressWarnings("rawtypes")
	/**
	 * �������Ĳ�ѯ
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 */
	public abstract ArrayList searchOBJ(String sql , Object[] params, Class clazz);
	/**
	 * �����ѯ���
	 * @param rSet
	 * @param clazz
	 * @return
	 */
	public abstract Object mapping(ResultSet rSet,Class<Object> clazz);
	/**
	 * ִ��һ�������ֲ���
	 * @param sql
	 * @param params
	 * @return���سɹ��޸ĵ�����
	 */
	public abstract int singleSQL(String sql,Object[] params);
	/**
	 * ֻ��һ�ֲ���ִ�ж��
	 * @param sql
	 * @param params
	 * @return�ɹ��޸ĵ���������
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
