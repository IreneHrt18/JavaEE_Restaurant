package DAO;

import java.util.ArrayList;

public interface SearchDAO {
	/**
	 * ͨ��������ѯ
	 * @param params
	 * @return ����arralist���� ����Ϊ��
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList searchByPrimaryKey(String[] params);
	/**
	 * ���ҵ�ǰҳ��Ԫ��
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList searchByPage(int currentPage,int pageSize);
}
