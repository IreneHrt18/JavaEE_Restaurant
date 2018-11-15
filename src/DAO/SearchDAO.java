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
}
