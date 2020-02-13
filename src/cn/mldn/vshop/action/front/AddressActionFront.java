package cn.mldn.vshop.action.front;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import cn.mldn.vshop.pojo.Address;
import cn.mldn.vshop.service.front.IAddressServiceFront;
import cn.mldn.vshop.util.action.AbstractAction;

@Repository
@ParentPackage("root")
@Namespace("/pages/front/center/address")
@Action("AddressActionFront")
@InterceptorRef("memberStack")
@SuppressWarnings("serial")
@Results(value = { @Result(name = "address.list.page", location = "/pages/front/center/address/address_list.jsp"), })
public class AddressActionFront extends AbstractAction {
	private Address address = new Address();

	public Address getAddress() {
		return address;
	}

	@Resource
	private IAddressServiceFront addressServiceFront;

	public void add() {
		String id = (String) super.getSession().getAttribute("id");
		try {
			boolean flag = this.addressServiceFront.add(id, this.address) ;
			super.print(flag + "|" + this.address.getAdid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editPre() {
		int adid = super.getParameterByInteger("adid");
		String id = (String) super.getSession().getAttribute("id");
		try {
			JSONObject obj = new JSONObject();
			Address vo = this.addressServiceFront.editPre(adid, id);
			obj.put("adid", vo.getAdid());
			obj.put("cid", vo.getCity().getCid());
			obj.put("pid", vo.getProvince().getPid());
			obj.put("addr", vo.getAddr());
			obj.put("receiver", vo.getReceiver());
			obj.put("phone", vo.getPhone());
			super.print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rm() {
		String id = (String) super.getSession().getAttribute("id");
		String ids = super.getRequest().getParameter("ids"); // 取得要删除的id编号
		String result[] = ids.split("\\|");
		Set<Integer> set = new HashSet<Integer>();
		for (int x = 0; x < result.length; x++) {
			set.add(Integer.parseInt(result[x]));
		}
		try {
			super.print(this.addressServiceFront.rm(id, set));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void edit() {
		String id = (String) super.getSession().getAttribute("id");
		try {
			super.print(this.addressServiceFront.edit(id, this.address));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String list() {
		String id = (String) super.getSession().getAttribute("id");
		try {
			super.getRequest().setAttribute("allAddresses",
					this.addressServiceFront.listByMember(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "address.list.page";
	}

	@Override
	public String getFlagName() {
		return "地址";
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
