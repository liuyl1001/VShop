package cn.mldn.util;

import cn.mldn.vshop.util.action.AbstractAction;

public class SplitUtil {
	private Integer currentPage = 1 ;
	private Integer lineSize = 8 ;
	private String column ;
	private String keyWord ;
	private AbstractAction action ;
	public SplitUtil(AbstractAction action) {
		this.action = action ;
	}
	public void setAllRecorders(Object obj) {
		this.action.getRequest().setAttribute("allRecorders", obj);
	} 
	public void setUrl(String urlKey) {
		this.action.getRequest().setAttribute("url",BasePathUtil.getBasePath(this.action.getRequest()) + this.action.getText(urlKey)); 
	}
	public void setCurrentPage(String param) {
		try {
			this.currentPage = Integer.parseInt(this.action.getRequest().getParameter(param));
		}catch (Exception e) {}
	}
	public void setLineSize(String param) {
		try {
			this.lineSize = Integer.parseInt(this.action.getRequest().getParameter(param));
		}catch (Exception e) {}
	}
	public void setColumn(String param) {
		if (this.action.getRequest().getParameter(param) == null || "".equals(this.action.getRequest().getParameter(param))) {
			this.column = this.action.getDefaultColumnt() ;
		} else {
			this.column = this.action.getRequest().getParameter(param) ;
		}
	}
	public void setKeyword(String param) {
		if (this.action.getRequest().getParameter(param) == null || "".equals(this.action.getRequest().getParameter(param))) {
			this.keyWord = "" ;
		} else {
			this.keyWord = this.action.getRequest().getParameter(param) ;
		}
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public Integer getLineSize() {
		return lineSize;
	}
	public String getColumn() {
		return column;
	}
	public String getKeyWord() {
		return keyWord;
	}
}
