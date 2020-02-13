package cn.mldn.vshop.action.front;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import cn.mldn.vshop.service.front.IGoodsServiceFront;
import cn.mldn.vshop.service.front.IShopcarServiceFront;
import cn.mldn.vshop.util.ShopcarCookieUtil;
import cn.mldn.vshop.util.action.AbstractAction;

@Repository
@ParentPackage("root")
@Namespace("/")
@Action("ShopcarActionFront")
@InterceptorRef("memberLoginStack")
@SuppressWarnings("serial")
@Results(value = {
		@Result(name = "shopcar.list.page", location = "/pages/front/shopcar_list.jsp"),
		@Result(name = "shopcar.member.list.page", location = "/pages/front/center/shopcar/shopcar_list.jsp") })
public class ShopcarActionFront extends AbstractAction {
	@Resource
	private IShopcarServiceFront shopcarServiceFront;
	@Resource
	private IGoodsServiceFront goodsServiceFront;

	public void add() { // 购物车的增加处理操作
		int gid = super.getParameterByInteger("gid"); // 需要的就是商品编号
		String mid = (String) super.getSession().getAttribute("id"); // 登录之后的数据
		ShopcarCookieUtil scu = new ShopcarCookieUtil();
		scu.add(gid); // 实现Cookie的保存
		if (mid != null) { // 现在已经存在有mid的数据，已经可以实现数据库的保存
			try {
				this.shopcarServiceFront.add(mid, gid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.print(true);
	}

	public String list() {
		String mid = (String) super.getSession().getAttribute("id"); // 登录之后的数据
		if (mid == null) { // 用户没有登录
			ShopcarCookieUtil scu = new ShopcarCookieUtil();
			Map<Integer, Integer> allGoods = scu.loadShopcar();
			super.getRequest().setAttribute("allGoods", allGoods);
			Set<Integer> gids = allGoods.keySet(); // 取得所有的key，这样就取得了所有的商品编号
			try { // 取得了所有的商品信息
				super.getRequest().setAttribute("all",
						this.goodsServiceFront.listCar(gids));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "shopcar.list.page";
		} else {// 列出当前用户所购买过的商品信息
			try {
				Map<String, Object> map = this.goodsServiceFront
						.listMemberCar(mid);
				super.getRequest().setAttribute("all", map.get("allGoods"));
				super.getRequest().setAttribute("allGoods", map.get("shopcar"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "shopcar.member.list.page";
		}
	}

	public void edit() { // 编辑整个的购物车数据
		String str = super.getRequest().getParameter("data"); // 接收数据
		String result[] = str.split("\\|"); // 实现数据的拆分处理
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int x = 0; x < result.length; x++) { // 循环控制
			String temp[] = result[x].split(":");
			int gid = Integer.parseInt(temp[0]);
			int amount = Integer.parseInt(temp[1]);
			if (amount > 0) {
				map.put(gid, amount);
			}
		}

		String id = (String) super.getSession().getAttribute("id");
		if (id != null) {
			try {
				this.shopcarServiceFront.edit(id, map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ShopcarCookieUtil scu = new ShopcarCookieUtil();
		scu.add(map);
		super.print(true);
	}

	public void rm() {
		String data = super.getRequest().getParameter("data"); // 多个id使用“|”分割
		String result[] = data.split("\\|");
		Set<Integer> set = new HashSet<Integer>();
		for (int x = 0; x < result.length; x++) {
			set.add(Integer.parseInt(result[x]));
		}
		String id = (String) super.getSession().getAttribute("id");
		if (id != null) {
			try {
				this.shopcarServiceFront.rm(id, set);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ShopcarCookieUtil scu = new ShopcarCookieUtil();
		scu.rm(set); // 删除数据
		super.print(true);
	}

	public void editSingle() { // 修改单独的一个商品数量
		int gid = super.getParameterByInteger("gid");
		int amount = super.getParameterByInteger("amount");
		String id = (String) super.getSession().getAttribute("id");
		if (id != null) { // 登录过了
			try {
				this.shopcarServiceFront.editSingle(id, gid, amount);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ShopcarCookieUtil scu = new ShopcarCookieUtil();
		scu.add(gid, amount);
		super.print(true);
	}

	@Override
	public String getFlagName() {
		return "购物车";
	}

	@Override
	public String getColumnData() {
		return null;
	}

	@Override
	public String getDefaultColumnt() {
		return "title";
	}
}
