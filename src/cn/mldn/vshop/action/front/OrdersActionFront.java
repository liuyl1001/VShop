package cn.mldn.vshop.action.front;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import cn.mldn.vshop.service.front.IOrdersServiceFront;
import cn.mldn.vshop.util.ShopcarCookieUtil;
import cn.mldn.vshop.util.action.AbstractAction;

@Repository
@ParentPackage("root")
@Namespace("/pages/front/center/orders")
@Action("OrdersActionFront")
@InterceptorRef("memberStack")
@SuppressWarnings("serial")
@Results(value = { 
		@Result(name = "orders.add.page", location = "/pages/front/center/orders/orders_add.jsp"),
		@Result(name = "orders.list.page", location = "/pages/front/center/orders/orders_list.jsp"),
		@Result(name = "orders.show.page", location = "/pages/front/center/orders/orders_details_show.jsp"),})
public class OrdersActionFront extends AbstractAction {
	@Resource
	private IOrdersServiceFront ordersServiceFront ;
	public String show() {
		String id = (String) super.getSession().getAttribute("id") ;
		int oid = super.getParameterByInteger("oid") ;
		try {
			Map<String,Object> map = this.ordersServiceFront.show(id, oid) ;
			super.getRequest().setAttribute("orders", map.get("orders"));
			super.getRequest().setAttribute("details", map.get("details"));
			super.getRequest().setAttribute("allGoods", map.get("allGoods"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "orders.show.page" ;
	}
	public String list() {
		String id = (String) super.getSession().getAttribute("id") ;
		super.handlSplit(); 	// 处理所有的分页信息。
		try {
			Map<String, Object> map = this.ordersServiceFront.listByMember(id,
					super.splitUtil.getCurrentPage(),
					super.splitUtil.getLineSize());
			super.getRequest().setAttribute("allOrderses", map.get("allOrderses"));
			super.splitUtil.setAllRecorders(map.get("ordersCount")); 
			super.splitUtil.setUrl("member.orders.list.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "orders.list.page" ;
	}
	
	public String add() {
		String id = (String) super.getSession().getAttribute("id") ;
		int adid = super.getParameterByInteger("adid") ;
		try {
			if (this.ordersServiceFront.add(id, adid)) {
				super.setMsgAndUrl("vo.add.success", "index.page");
				ShopcarCookieUtil scu = new ShopcarCookieUtil() ;
				scu.clear(); 
			} else {
				super.setMsgAndUrl("vo.add.failure", "index.page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgAndUrl("vo.add.failure", "index.page");
		}
		return "forward.page" ;
	}
	
	public String addPre() {	// 创建订单数据显示
		String id = (String) super.getSession().getAttribute("id") ;
		try {
			Map<String,Object> map = this.ordersServiceFront.addPre(id) ;
			boolean flag = (Boolean) map.get("flag") ;
			if (flag) {	// 现在有数据，正常将内容提交给订单创建页
				super.getRequest().setAttribute("allAddresses", map.get("allAddresses"));
				super.getRequest().setAttribute("carGoods", map.get("carGoods"));
				super.getRequest().setAttribute("allCar", map.get("allCar"));
				return "orders.add.page" ;
			} else {
				super.setMsgAndUrl("member.orders.error", "index.page");
				return "forward.page" ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "index.page" ;
	}
	
	@Override
	public String getFlagName() {
		return "用户订单";
	}

	@Override
	public String getColumnData() {
		return null;
	}

	@Override
	public String getDefaultColumnt() {
		return null;
	}

}
