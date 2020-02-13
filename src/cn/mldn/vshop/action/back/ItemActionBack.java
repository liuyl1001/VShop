package cn.mldn.vshop.action.back;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import cn.mldn.vshop.pojo.Item;
import cn.mldn.vshop.service.back.IItemServiceBack;
import cn.mldn.vshop.util.action.AbstractAction;

@Repository
@ParentPackage("root")
@Namespace("/pages/back/admin/item")
@Action("ItemActionBack")
@InterceptorRef("adminStack")
@SuppressWarnings("serial")
@Results(value = { @Result(name = "item.list.page", location = "/pages/back/admin/item/item_list.jsp") })
public class ItemActionBack extends AbstractAction {
	private Item item = new Item() ;
	@Resource
	private IItemServiceBack itemServiceBack; 
	public String list() {
		try {
			super.getRequest()
					.setAttribute("allItems", this.itemServiceBack.list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "item.list.page";
	}
	
	public void edit() {
		try {
			super.print(this.itemServiceBack.edit(this.item));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Item getItem() {
		return item;
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
