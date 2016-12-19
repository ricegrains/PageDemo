package org.pagedemo.page;

/**
 * Create on 2008-08-28 11:05
 * 
 * @author qpy_2006
 * 要用到分页，则在创建该类对象时
 * 只需在传入rows的前提下调用counter(page,rows)方法就可以设置firstRow，totalRows,totalPages
 * 在查询的dao中，把该类对象做为参数传入，
 * 再通过调用对象的getFirstRow(),及getMaxSize()设置
 */
public class PageUtil {

	private int totalRows;	// 总行数即总记录数
	private int totalPages;	// 总页数
	private int currentPage = 1; // 当前页
	private int maxSize = 3; // 每页存放3条记录
	private int firstRow = 0; // 数据库中第一行数据

	/** Creates a new instance of PageCounter */
	public PageUtil() {
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	// 根据请求参数pageType翻页,即设置firstRow
	// 根据请求参数rows总记录数计算总页数
	public void counter(String pageType,int rows) {
		this.totalRows = rows;
		this.totalPages = (this.totalRows + this.maxSize - 1) / this.maxSize;
		System.out.println("counter 中的totalPages的值为 "+this.totalPages+" 页");
		pageType = pageType.toLowerCase();
		if ("first".equals(pageType)) {
			first();
		} else if ("last".equals(pageType)) {
			last();
		} else if ("next".equals(pageType)) {
			next();
		} else if ("previous".equals(pageType)) {
			previous();
		}
	}

	// 首页
	private void first() {
		currentPage = 1;
		firstRow = 0;
	}

	// 上页
	private void previous() {
		if (totalPages == 0) {
			currentPage = 1;
		} else {
			if (currentPage == 1) {
				currentPage = totalPages;
			} else {
				currentPage--;
			}
		}
		firstRow = (currentPage - 1) * maxSize;
	}

	// 下页
	private void next() {
		if (totalPages == 0) {
			currentPage = 1;
		} else {
			if (currentPage < totalPages) {
				currentPage++;
			} else if (currentPage == totalPages) {
				currentPage = 1;
			}
		}
		firstRow = (currentPage - 1) * maxSize;
	}

	// 尾页
	private void last() {
		currentPage = totalPages;
		if (totalPages == 0) {
			currentPage = 1;
		}
		firstRow = (currentPage - 1) * maxSize;
	}
}
