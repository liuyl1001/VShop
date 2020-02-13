package cn.mldn.vshop.action.back;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import cn.mldn.vshop.service.back.IOrdersServiceBack;
import cn.mldn.vshop.util.action.AbstractAction;
@Repository
@ParentPackage("root")
@Namespace("/pages/back/admin/orders")
@Action("OrdersActionBack")
@InterceptorRef("adminStack")
@SuppressWarnings("serial")
@Results(value = { 
		@Result(name = "orders.list.page", location = "/pages/back/admin/orders/orders_list.jsp"),
		@Result(name = "orders.show.page", location = "/pages/back/admin/orders/orders_details_show.jsp")})
public class OrdersActionBack extends AbstractAction {
	@Resource
	private IOrdersServiceBack ordersServiceBack ;
	public String list() {
		super.handlSplit(); 	// 处理所有的分页信息。
		try {
			Map<String, Object> map = this.ordersServiceBack.list(
					super.splitUtil.getColumn(), super.splitUtil.getKeyWord(),
					super.splitUtil.getCurrentPage(),
					super.splitUtil.getLineSize());
			super.getRequest().setAttribute("allOrders", map.get("allOrders"));
			super.splitUtil.setAllRecorders(map.get("ordersCount")); 
			super.splitUtil.setUrl("admin.orders.list.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "orders.list.page";
	}
	
	public String show() {
		int oid = super.getParameterByInteger("oid") ;
		try {
			Map<String,Object> map = this.ordersServiceBack.show(oid) ;
			super.getRequest().setAttribute("orders", map.get("orders"));
			super.getRequest().setAttribute("details", map.get("details"));
			super.getRequest().setAttribute("allGoods", map.get("allGoods"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "orders.show.page" ;
	}
	
	@Override
	public String getFlagName() {
		return "订单";
	}


	@Override
	public String getColumnData() {
		return null;
	}


	@Override
	public String getDefaultColumnt() {
		return "member.mid";
	}
}
