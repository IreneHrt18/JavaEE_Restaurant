package DAO;

public interface DeleteDAO {
	/**
	 * 接受多个主键值 批次删除
	 * @param primaryKey
	 * @return int 修改了多少列
	 */
	public int[] deleteByPrimaryKey(String[] primaryKey);
}
