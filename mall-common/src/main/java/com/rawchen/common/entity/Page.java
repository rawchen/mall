package com.rawchen.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rawchen.common.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author RawChen
 * @since 2021-10-31 0:16
 */
public class Page<T> implements Serializable {
	protected Logger logger;
	private static final long serialVersionUID = 1L;
	protected int pageNo;
	protected int pageSize;
	protected long count;
	protected int first;
	protected int last;
	protected int prev;
	protected int next;
	private boolean firstPage;
	private boolean lastPage;
	protected int length;
	protected int slider;
	protected int pageTotal;
	protected int pageIndex;
	private List<T> list;
	private String orderBy;
	protected String funcName;
	protected String funcParam;
	private String message;

	public Page() {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.pageNo = 1;
		this.pageSize = 10;
		this.length = 8;
		this.slider = 1;
		this.list = new ArrayList();
		this.orderBy = "";
		this.funcName = "page";
		this.funcParam = "";
		this.message = "";
		this.pageSize = -1;
	}

	public Page(HttpServletRequest request, HttpServletResponse response) {
		this(request, response, -2);
	}

	public Page(HttpServletRequest request, HttpServletResponse response, int defaultPageSize) {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.pageNo = 1;
		this.pageSize = 10;
		this.length = 8;
		this.slider = 1;
		this.list = new ArrayList();
		this.orderBy = "";
		this.funcName = "page";
		this.funcParam = "";
		this.message = "";
		String no = this.getParam(request, "pageNo");
		if (StringUtils.isNumeric(no)) {
			CookieUtil.setCookie(response, "pageNo", no);
			this.setPageNo(Integer.parseInt(no));
		} else if (this.getParam(request, "repage") != null) {
			no = CookieUtil.getCookie(request, "pageNo");
			if (StringUtils.isNumeric(no)) {
				this.setPageNo(Integer.parseInt(no));
			}
		}

		String size = this.getParam(request, "pageSize");
		if ("-1".equals(size)) {
			size = "65535";
		}

		if (StringUtils.isNumeric(size)) {
			CookieUtil.setCookie(response, "pageSize", size);
			this.setPageSize(Integer.parseInt(size));
		} else if (this.getParam(request, "repage") != null) {
			no = CookieUtil.getCookie(request, "pageSize");
			if (StringUtils.isNumeric(size)) {
				this.setPageSize(Integer.parseInt(size));
			}
		} else if (defaultPageSize != -2) {
			this.pageSize = defaultPageSize;
		}

		String orderBy = this.getParam(request, "orderBy");
		if (StringUtils.isNotBlank(orderBy)) {
			this.setOrderBy(orderBy);
		}

	}

	public String getParam(HttpServletRequest request, String key) {
		String obj = request.getParameter(key);
		if (StringUtils.isEmpty(obj)) {
			obj = request.getHeader(key);
		}

		return obj;
	}

	public Page(int pageNo, int pageSize) {
		this(pageNo, pageSize, 0L);
	}

	public Page(int pageNo, int pageSize, long count) {
		this(pageNo, pageSize, count, new ArrayList());
	}

	public Page(int pageNo, int pageSize, long count, List<T> list) {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.pageNo = 1;
		this.pageSize = 10;
		this.length = 8;
		this.slider = 1;
		this.list = new ArrayList();
		this.orderBy = "";
		this.funcName = "page";
		this.funcParam = "";
		this.message = "";
		this.setCount(count);
		this.setPageNo(pageNo);
		this.pageSize = pageSize;
		this.list = list;
	}

	public void initialize() {
		this.first = 1;
		this.last = (int)(this.count / (long)(this.pageSize < 1 ? 20 : this.pageSize) + (long)this.first - 1L);
		if (this.count % (long)this.pageSize != 0L || this.last == 0) {
			++this.last;
		}

		if (this.last < this.first) {
			this.last = this.first;
		}

		if (this.pageNo <= 1) {
			this.pageNo = this.first;
			this.firstPage = true;
		}

		if (this.pageNo >= this.last) {
			this.pageNo = this.last;
			this.lastPage = true;
		}

		if (this.pageNo < this.last - 1) {
			this.next = this.pageNo + 1;
		} else {
			this.next = this.last;
		}

		if (this.pageNo > 1) {
			this.prev = this.pageNo - 1;
		} else {
			this.prev = this.first;
		}

		if (this.pageNo < this.first) {
			this.pageNo = this.first;
		}

		if (this.pageNo > this.last) {
			this.pageNo = this.last;
		}

		this.pageTotal = (int)(this.count % (long)this.pageSize == 0L ? this.count / (long)this.pageSize : this.count / (long)this.pageSize + 1L);
	}

	public String toString() {
		return "Page [pageNo=" + this.pageNo + ", pageSize=" + this.pageSize + ", count=" + this.count + ", list=" + this.list + "]";
	}

	protected String getSelected(int pageNo, int selectedPageNo) {
		return pageNo == selectedPageNo ? "active" : "";
	}

	public long getCount() {
		return this.count;
	}

	public void setCount(long count) {
		this.count = count;
		if ((long)this.pageSize >= count) {
			this.pageNo = 1;
		}

	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getPageTotal() {
		return this.pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageIndex() {
		return this.list == null ? 0 : this.list.size();
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 0 ? 10 : pageSize;
	}

	@JsonIgnore
	public int getFirst() {
		return this.first;
	}

	@JsonIgnore
	public int getLast() {
		return this.last;
	}

	@JsonIgnore
	public int getTotalPage() {
		return this.getLast();
	}

	@JsonIgnore
	public boolean isFirstPage() {
		return this.firstPage;
	}

	@JsonIgnore
	public boolean isLastPage() {
		return this.lastPage;
	}

	@JsonIgnore
	public int getPrev() {
		return this.isFirstPage() ? this.pageNo : this.pageNo - 1;
	}

	@JsonIgnore
	public int getNext() {
		return this.isLastPage() ? this.pageNo : this.pageNo + 1;
	}

	public List<T> getList() {
		return this.list;
	}

	public Page<T> setList(List<T> list) {
		this.list = list;
		this.initialize();
		return this;
	}

	@JsonIgnore
	public String getOrderBy() {
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		Pattern sqlPattern = Pattern.compile(reg, 2);
		return sqlPattern.matcher(this.orderBy).find() ? "" : this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@JsonIgnore
	public String getFuncName() {
		return this.funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	@JsonIgnore
	public String getFuncParam() {
		return this.funcParam;
	}

	public void setFuncParam(String funcParam) {
		this.funcParam = funcParam;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonIgnore
	public boolean isDisabled() {
		return this.pageSize == -1;
	}

	@JsonIgnore
	public boolean isNotCount() {
		return this.count == -1L;
	}

	public int getFirstResult() {
		int firstResult = (this.getPageNo() - 1) * this.getPageSize();
		if ((long)firstResult >= this.getCount() || firstResult < 0) {
			firstResult = 0;
		}

		return firstResult;
	}

	public int getMaxResults() {
		return this.getPageSize();
	}
}