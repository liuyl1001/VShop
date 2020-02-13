package cn.mldn.vshop.action.back;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import cn.mldn.vshop.pojo.Subitem;
import cn.mldn.vshop.service.back.ISubitemServiceBack;
import cn.mldn.vshop.util.action.AbstractAction;

@Repository
@ParentPackage("root")
@Namespace("/pages/back/admin/item")
@Action("SubitemActionBack")
@InterceptorRef("adminStack")
@SuppressWarnings("serial")
@Results(value = { @Result(name = "subitem.list.page", location = "/pages/back/admin/item/subitem_list.jsp") })
public class SubitemActionBack extends AbstractAction {
	@Resource
	private ISubitemServiceBack subitemServiceBack;
	private Subitem subitem = new Subitem() ;
	public Subitem getSubitem() {
		return subitem;
	}
	public String list() {
		try {
			super.getRequest().setAttribute(
					"allSubitems",
					this.subitemServiceBack.listByItem(super
							.getParameterByInteger("iid")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "subitem.list.page";
	}

	public void edit() {
		try {
			super.print(this.subitemServiceBack.edit(this.subitem)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getFlagName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getColumnData() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDefaultColumnt() {
		// TODO Auto-generated method stub
		return null;
	}
}
