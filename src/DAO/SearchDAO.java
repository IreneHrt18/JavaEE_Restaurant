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
}
