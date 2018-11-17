package Bean;

import java.util.ArrayList;

public class PageModel {
	private int currentPageNum;
	private int totalPageNum;
	private  int pageSize;
	private boolean isSelect;
	@SuppressWarnings("rawtypes")
	private ArrayList list;
	/**
	 * 构造函数
	 * @param currentPageNum
	 * @param totalPageNum
	 * @param pageSize
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	public PageModel(int currentPageNum,int totalPageNum,int pageSize,ArrayList list) {
		// TODO Auto-generated constructor stub
		this.currentPageNum =currentPageNum;
		this.totalPageNum = totalPageNum;
		this.pageSize = pageSize;
		this.list = list;
		this.isSelect = false;
	}
	/**
	 * 获得下一页
	 * @return
	 */
	public int getNext() {
		if (currentPageNum < getTotalPageNum()) {
			return currentPageNum + 1;
		} else {
			return getTotalPageNum();
		}
	}
	/**
	 * 获得上一页
	 * @return
	 */
	public int getPrev() {
		if (currentPageNum > 1) {
			return currentPageNum - 1;
		} else
			return 1;
	}
	/**
	 * 首页
	 * @return
	 */
	public int getTop() {
		return 1;
	}
	/**
	 * 尾页
	 * @return
	 */
	public int getBottom() {
		return getTotalPageNum();
	}
	/**
	 * 当前页
	 * @return
	 */
	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList getList() {
		return list;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@SuppressWarnings("rawtypes")
	public void setList(ArrayList list) {
		this.list = list;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}
}
