package cn.mldn.vshop.action.back;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import cn.mldn.util.FileOperate;
import cn.mldn.vshop.pojo.Goods;
import cn.mldn.vshop.service.back.IGoodsServiceBack;
import cn.mldn.vshop.util.action.AbstractAction;

@Repository
@ParentPackage("root")
@Namespace("/pages/back/admin/goods")
@Action("GoodsActionBack")
@InterceptorRef("adminUploadStack")
@SuppressWarnings("serial")
@Results(value = { 
		@Result(name = "goods.add.page", location = "/pages/back/admin/goods/goods_add.jsp") ,
		@Result(name = "goods.list.page", location = "/pages/back/admin/goods/goods_list.jsp") ,
		@Result(name = "goods.edit.page", location = "/pages/back/admin/goods/goods_edit.jsp")})
public class GoodsActionBack extends AbstractAction {
	private static final String FILE_DIR = "upload/goods/" ;
	private File photo ;	// 定义上传文件
	private String photoContentType ;	// 接收MIME类型 
	private Goods goods = new Goods() ;
	public Goods getGoods() {
		return goods;
	}
	@Resource
	private IGoodsServiceBack goodsServiceBack; 
	public String addPre() {
		return "goods.add.page" ;
	}
	public void show() {
		int gid = super.getParameterByInteger("gid") ;	// 取得传递过来的gid数据
		try {
			Goods vo = this.goodsServiceBack.show(gid) ;
			if (vo != null) {// 取得指定编号的详情
				JSONObject obj = new JSONObject() ;
				obj.put("title", vo.getTitle()) ;
				obj.put("photo", vo.getPhoto()) ;
				obj.put("price", vo.getPrice()) ;
				obj.put("mid", vo.getMember().getMid()) ;
				obj.put("pubdate", super.dateFormat(vo.getPubdate())) ;
				obj.put("iid", vo.getItem().getIid()) ;
				obj.put("ititle", vo.getItem().getTitle()) ;
				obj.put("sid", vo.getSubitem().getSid()) ;
				obj.put("stitle", vo.getSubitem().getTitle()) ;
				obj.put("note", vo.getNote()) ;
				super.print(obj); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public String rm() {
		try {
			if (this.goodsServiceBack.rm(super.getIdsByInteger())) {
				super.setMsgAndUrl("vo.rm.success", "admin.goods.list.action");
			} else {
				super.setMsgAndUrl("vo.rm.failure", "admin.goods.list.action");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgAndUrl("vo.rm.failure", "admin.goods.list.action");
		}
		return "forward.page" ;
	}
	public String editPre() {
		try {
			super.getRequest().setAttribute("goods", this.goodsServiceBack.editPre(this.goods.getGid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goods.edit.page" ;
	}
	public String edit() {
		try {
			if (this.photo.length() > 0) {	// 现在要进行图片的更新处经理
				if ("nophoto.png".equals(this.goods.getPhoto())) {	// 原始没有图片名称
					this.goods.setPhoto(FileOperate.createFileName(this.photoContentType));
				}
			}
			if (this.goodsServiceBack.edit(this.goods)) {
				if (this.photo.length() > 0) {	// 更新图片
					FileOperate.saveFile(FILE_DIR + this.goods.getPhoto(), this.photo) ;	// 文件保存
				}
				super.setMsgAndUrl("vo.edit.success", "admin.goods.list.action");
			} else {
				super.setMsgAndUrl("vo.edit.failure", "admin.goods.list.action");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgAndUrl("vo.edit.failure", "admin.goods.list.action");
		}
		return "forward.page" ;
	} 
	public String list() {
		super.handlSplit(); 	// 处理所有的分页信息。
		try {
			Map<String, Object> map = this.goodsServiceBack.list(
					super.splitUtil.getColumn(), super.splitUtil.getKeyWord(),
					super.splitUtil.getCurrentPage(),
					super.splitUtil.getLineSize());
			super.getRequest().setAttribute("allGoodss", map.get("allGoodss"));
			super.splitUtil.setAllRecorders(map.get("goodsCount")); 
			super.splitUtil.setUrl("admin.goods.list.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goods.list.page" ;
	}
	public String add() {
		try {
			// 取得已经保存在session中的mid数据
			String mid = (String) super.getSession().getAttribute("mid") ;
			// 取得生成的文件名称，并且将其保存在Goods类对象之中
			if (this.photo.length() > 0){
				this.goods.setPhoto(FileOperate.createFileName(this.photoContentType));
			} else {
				this.goods.setPhoto("nophoto.png");
			}
			this.goods.getMember().setMid(mid);
			if (this.goodsServiceBack.add(this.goods)) {
				if (this.photo.length() > 0){
					FileOperate.saveFile(FILE_DIR + this.goods.getPhoto(), this.photo) ;	// 文件保存
				}
				super.setMsgAndUrl("vo.add.success", "admin.goods.add.action");
			} else {
				super.setMsgAndUrl("vo.add.failure", "admin.goods.add.action");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgAndUrl("vo.add.failure", "admin.goods.add.action");
		}
		return "forward.page";
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
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
