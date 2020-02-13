package cn.mldn.vshop.util.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.mldn.util.SplitUtil;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public abstract class AbstractAction extends ActionSupport {
	protected SplitUtil splitUtil ;
	/**
	 * 批量操作的参数名称固定为ids
	 * @return
	 */
	public Set<Integer> getIdsByInteger() {
		String ids = this.getRequest().getParameter("ids") ;
		Set<Integer> all = new HashSet<Integer>() ;
		try {
			String result [] = ids.split("\\|") ;
			for (int x = 0 ; x < result.length ; x ++) {
				all.add(Integer.parseInt(result[x])) ;
			}
		} catch(Exception e) {}
		return all ;
	}
	public String dateFormat(Date date) {
		if (date != null) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) ;
		}
		return "" ;
	}
	/**
	 * 直接返回request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest() ;
	}
	/**
	 * 直接返回response对象
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse() ;
	}
	/**
	 * 输出信息，可能是字符串，可能是JSON
	 * @param obj
	 */
	public void print(Object obj) {
		try {
			this.getRequest().setCharacterEncoding("UTF-8");
			this.getResponse().setCharacterEncoding("UTF-8");
			this.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 接收参数
	 * @param paramName
	 * @return 如果返回的内容是-1，则表示操作出现了错误
	 */
	public int getParameterByInteger(String paramName) {	// 接收参数自动进行转换
		int num = -1 ;
		try {
			num = Integer.parseInt(this.getRequest().getParameter(paramName)) ;
		}catch(Exception e) {}
		return num ;
	}
	/**
	 * 取得session对象
	 * @return
	 */
	public HttpSession getSession() {
		return this.getRequest().getSession() ;
	}
	/**
	 * 通过request属性设置msg与url两个参数内容
	 * @param msg 操作的提示信息，通过Messages.properties查找
	 * @param url 跳转的路径信息，通过Pages.properties查找
	 */
	public void setMsgAndUrl(String msg,String url) {
		this.getRequest().setAttribute("msg", super.getText(msg,new String[]{this.getFlagName()}));
		this.getRequest().setAttribute("url", super.getText(url,new String[]{this.getFlagName()}));
	}
	/**
	 * 描述的是一个具体的名称，例如，如果要操作的是商品，则返回商品信息，与提示信息进行组合
	 * @return
	 */
	public abstract String getFlagName() ;
	/**
	 * 取得默认的关键字搜索时所需要使用到的列的信息，按照“列名称:字段|列名称:字段”的形式描述
	 * @return
	 */
	public abstract String getColumnData() ;
	/**
	 * 返回一个默认的分页的操作列，是在没有设置列的时候使用的
	 * @return
	 */
	public abstract String getDefaultColumnt() ;
	/**
	 * 进行分页的处理操作，处理完成之后将确定col、kw、cp、ls几个参数的内容可用
	 */
	public void handlSplit() {	// 处理分页操作
		this.splitUtil = new SplitUtil(this) ;
		this.splitUtil.setCurrentPage("cp");	// 接收并处理参数
		this.splitUtil.setLineSize("ls");	// 接收并处理参数
		this.splitUtil.setColumn("col");	// 接收并处理参数
		this.splitUtil.setKeyword("kw");	// 接收并处理参数
		// 需要将所有的内容以request属性范围进行传递
		this.getRequest().setAttribute("currentPage", this.splitUtil.getCurrentPage());
		this.getRequest().setAttribute("lineSize", this.splitUtil.getLineSize());
		this.getRequest().setAttribute("column", this.splitUtil.getColumn());
		this.getRequest().setAttribute("keyWord", this.splitUtil.getKeyWord());
		this.getRequest().setAttribute("columnData", this.getColumnData());
	}
}
