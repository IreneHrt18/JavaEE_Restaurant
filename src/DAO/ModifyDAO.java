package DAO;

public interface ModifyDAO {
	/**
	 * 修改图片链接
	 * @param params
	 * @return int
	 */
	public int modifyImgUrl(String[] params);
	/**
	 * 修改除主键外的所有值
	 * @param primary
	 * @param params
	 * @return
	 */
	public int modifyAllByPrimarykey(String primary,String[] params);
}
