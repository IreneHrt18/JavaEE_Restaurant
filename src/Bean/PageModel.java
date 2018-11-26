package Bean;

public class PageModel {
	private int currentPageNum;
	private int totalPageNum;
	private static int pageSize;
	private int totalCount;
	private int pageGap;
	static {
		pageSize = 10;
	}
	
	public PageModel(int count) {
		// TODO Auto-generated constructor stub
		this.currentPageNum = 1;
		this.pageGap = 1;
		this.totalCount = count;
		this.totalPageNum = (pageSize+count -1)/pageSize ;
	}
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public static int getPageSize() {
		return pageSize;
	}
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public static void setPageSize(int pageSize) {
		PageModel.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageGap() {
		return pageGap;
	}
	public void setPageGap(int pageGap) {
		this.pageGap = pageGap;
	}

	
	
	
}
