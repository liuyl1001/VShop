package cn.mldn.vshop.action.back;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import cn.mldn.util.MD5Code;
import cn.mldn.vshop.pojo.Address;
import cn.mldn.vshop.pojo.Member;
import cn.mldn.vshop.service.back.IMemberServiceBack;
import cn.mldn.vshop.util.action.AbstractAction;
@Repository
@ParentPackage("root")
@Namespace("/pages/back/admin/member")
@Action("MemberActionBack")
@InterceptorRef("adminStack")
@SuppressWarnings("serial")
@Results(value = { 
		@Result(name = "member.list.page", location = "/pages/back/admin/member/member_list.jsp") })
public class MemberActionBack extends AbstractAction {
	@Resource
	private IMemberServiceBack memberServiceBack ;
	private Member member = new Member() ;
	public Member getMember() {
		return member;
	} 
	
	public void edit() {
		this.member.setPassword(new MD5Code().getMD5ofStr(this.member.getPassword()));
		try {
			super.print(this.memberServiceBack.editPassword(this.member));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void lock() {
		String str = super.getRequest().getParameter("ids") ;	// 接收所有要修改的数据
		if (str == null || "".equals(str)) {
			super.print(false);
		} else {
			String result [] = str.split("\\|") ;
			Set<String> mids = new HashSet<String>() ;
			for (int x = 0 ; x < result.length ; x ++) {
				mids.add(result[x]) ;
			}
			try {
				super.print(this.memberServiceBack.editLock(mids));
			} catch (Exception e) {
				e.printStackTrace();
				super.print(false);
			}
		}
	} 
	
	public void show() {	// 实现数据的显示处理
		try {	// 根据指定的雇员编号取得他的完整数据
			Map<String,Object> map = this.memberServiceBack.show(this.member.getMid()) ;
			Member vo = (Member) map.get("member") ;
			JSONObject obj = new JSONObject() ;
			JSONArray array = new JSONArray() ;
			obj.put("mid", vo.getMid()) ;
			obj.put("phone", vo.getPhone()) ;
			obj.put("email", vo.getEmail()) ;
			obj.put("reg", super.dateFormat(vo.getReg())) ; 
			Iterator<Address> iter = ((List<Address>) map.get("allAddress")).iterator() ; 
			while (iter.hasNext()) {
				Address addr = iter.next() ;
				JSONObject temp = new JSONObject() ;
				temp.put("receiver", addr.getReceiver()) ;
				temp.put("phone", addr.getPhone()) ;
				temp.put("addr", addr.getAddr()) ;
				temp.put("deflag", addr.getDeflag()) ;
				array.add(temp) ;
			}
			obj.put("allAddres", array) ;
			super.print(obj); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public String list() {
		super.handlSplit(); 	// 处理所有的分页信息。
		try {
			Map<String, Object> map = this.memberServiceBack.list(
					super.splitUtil.getColumn(), super.splitUtil.getKeyWord(),
					super.splitUtil.getCurrentPage(),
					super.splitUtil.getLineSize());
			super.getRequest().setAttribute("allMembers", map.get("allMembers"));
			super.splitUtil.setAllRecorders(map.get("memberCount")); 
			super.splitUtil.setUrl("admin.member.list.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member.list.page";
	}
	
	@Override
	public String getFlagName() {
		return "用户";
	}


	@Override
	public String getColumnData() {
		return "登录ID:mid|邮箱:email|联系电话:phone";
	}


	@Override
	public String getDefaultColumnt() {
		return "mid";
	}
}
