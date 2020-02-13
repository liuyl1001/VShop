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

import cn.mldn.vshop.service.front.IGoodsServiceFront;
import cn.mldn.vshop.util.action.AbstractAction;

@Repository
@ParentPackage("root")
@Namespace("/pages/front/goods")
@Action("GoodsActionFront")
@InterceptorRef("memberLoginStack")
@SuppressWarnings("serial")
@Results(value = { 
		@Result(name = "goods.list.page", location = "/pages/front/goods/goods_list.jsp") ,
		@Result(name = "goods.show.page", location = "/pages/front/goods/goods_show.jsp")
		})  
public class GoodsActionFront extends AbstractAction {
	@Resource
	private IGoodsServiceFront goodsServiceFront; 
//	public void show() {
//		int gid = super.getParameterByInteger("gid") ;	// 取得传递过来的gid数据
//		try {
//			Goods vo = this.goodsServiceBack.show(gid) ;
//			if (vo != null) {// 取得指定编号的详情
//				JSONObject obj = new JSONObject() ;
//				obj.put("title", vo.getTitle()) ;
//				obj.put("photo", vo.getPhoto()) ;
//				obj.put("price", vo.getPrice()) ;
//				obj.put("mid", vo.getMember().getMid()) ;
//				obj.put("pubdate", super.dateFormat(vo.getPubdate())) ;
//				obj.put("iid", vo.getItem().getIid()) ;
//				obj.put("ititle", vo.getItem().getTitle()) ;
//				obj.put("sid", vo.getSubitem().getSid()) ;
//				obj.put("stitle", vo.getSubitem().getTitle()) ;
//				obj.put("note", vo.getNote()) ;
//				super.print(obj); 
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//	}
	
	public String listBySubitem() {
		super.handlSplit(); 	// 处理所有的分页信息。
		try {
			int sid = super.getParameterByInteger("sid") ;
			Map<String, Object> map = this.goodsServiceFront.listBySubitem(sid,
					super.splitUtil.getColumn(), super.splitUtil.getKeyWord(),
					super.splitUtil.getCurrentPage(),
					super.splitUtil.getLineSize());
			super.getRequest().setAttribute("allGoodss", map.get("allGoodss"));
			super.getRequest().setAttribute("paramName", "sid");
			super.getRequest().setAttribute("paramValue", String.valueOf(sid));
			super.splitUtil.setAllRecorders(map.get("goodsCount")); 
			super.splitUtil.setUrl("member.goods.list.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goods.list.page" ;
	}
	
	public String listSearch() {
		super.handlSplit(); 	// 处理所有的分页信息。
		try {
			Map<String, Object> map = this.goodsServiceFront.listSearch(
					super.splitUtil.getColumn(), super.splitUtil.getKeyWord(),
					super.splitUtil.getCurrentPage(),
					super.splitUtil.getLineSize());
			super.getRequest().setAttribute("allGoodss", map.get("allGoodss"));
			super.splitUtil.setAllRecorders(map.get("goodsCount")); 
			super.splitUtil.setUrl("member.goods.listSearch.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goods.list.page" ;
	}
	public String show() {
		int gid = super.getParameterByInteger("gid") ;
		try {
			super.getRequest().setAttribute("goods", this.goodsServiceFront.show(gid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goods.show.page" ;
	}
	
	@Override
	public String getFlagName() {
		return "商品";
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
