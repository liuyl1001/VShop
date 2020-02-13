package cn.mldn.vshop.action.back;

import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Repository;

import cn.mldn.vshop.service.back.IIndexServiceBack;
import cn.mldn.vshop.util.action.AbstractAction;
@Repository
@ParentPackage("root")
@Namespace("/pages/back/admin")
@Action("IndexActionBack")
@InterceptorRef("adminStack") 
@SuppressWarnings("serial")
public class IndexActionBack extends AbstractAction {
	@Resource
	private IIndexServiceBack indexServiceBack ;
	public void show() {
		try {
			Map<String,Object> map = this.indexServiceBack.show() ;
			JSONObject obj = new JSONObject() ;
			obj.put("goodsCount", map.get("goodsCount")) ;
			obj.put("ordersCount", map.get("ordersCount")) ;
			obj.put("todayCount", map.get("todayCount")) ;
			super.print(obj);
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
