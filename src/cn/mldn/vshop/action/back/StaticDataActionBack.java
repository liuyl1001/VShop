package cn.mldn.vshop.action.back;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Repository;

import cn.mldn.vshop.pojo.City;
import cn.mldn.vshop.pojo.Goods;
import cn.mldn.vshop.pojo.Item;
import cn.mldn.vshop.pojo.Province;
import cn.mldn.vshop.pojo.Subitem;
import cn.mldn.vshop.service.back.IGoodsServiceBack;
import cn.mldn.vshop.service.back.IItemServiceBack;
import cn.mldn.vshop.service.back.IProvinceServiceBack;
import cn.mldn.vshop.util.StaticDataFile;
import cn.mldn.vshop.util.action.AbstractAction;

@Repository
@ParentPackage("root")
@Namespace("/pages/back/admin")
@Action("StaticDataActionBack")
@InterceptorRef("adminStack")
@SuppressWarnings("serial")
public class StaticDataActionBack extends AbstractAction {
	
	@Resource
	private IItemServiceBack itemServiceBack; 
	@Resource
	private IGoodsServiceBack goodsServiceBack ; 
	@Resource
	private IProvinceServiceBack provinceServiceBack ;
	public void listGoods() {	// 生成商品数据信息
		try {
			List<Goods> all = this.goodsServiceBack.listStatic("title", "", 1, 8) ;
			JSONObject obj = new JSONObject() ;
			JSONArray arr = new JSONArray() ;
			Iterator<Goods> iter = all.iterator() ;
			while (iter.hasNext()) {
				Goods vo = iter.next() ;
				JSONObject temp = new JSONObject() ;
				temp.put("gid", vo.getGid()) ;
				temp.put("photo", vo.getPhoto()) ;
				temp.put("price", vo.getPrice()) ;
				temp.put("title", vo.getTitle()) ;
				temp.put("iid", vo.getItem().getIid()) ;
				temp.put("sid", vo.getSubitem().getSid()) ;
				arr.add(temp) ;
			}
			obj.put("allGoods", arr) ;
			StaticDataFile.saveGoodsFile(obj);
			super.print(true);
		} catch (Exception e) {
			e.printStackTrace();
			super.print(false); 
		}
	} 
	
	public void listProvince() {	// 进行数据列表
		try {
			JSONArray proArr = new JSONArray() ;
			List<Province> all = this.provinceServiceBack.list() ;
			Iterator<Province> iter = all.iterator() ;
			while (iter.hasNext()) {
				Province pro = iter.next() ;	// 取得省份内容
				JSONObject proTemp = new JSONObject() ;
				proTemp.put("pid", pro.getPid()) ;
				proTemp.put("title", pro.getTitle()) ;
				proArr.add(proTemp) ;
				// 处理每一个省份对应的城市数据
				JSONArray cityArr = new JSONArray() ;
				Set<City> allCity = pro.getCities() ;
				Iterator<City> iterC = allCity.iterator() ;
				while(iterC.hasNext()) {
					City c = iterC.next() ;
					JSONObject cTemp = new JSONObject() ;
					cTemp.put("cid", c.getCid()) ;
					cTemp.put("title", c.getTitle()) ;
					cityArr.add(cTemp) ;
				}
				// 向city-x.txt中实现数据保存
				StaticDataFile.saveCityFile(pro.getPid(), cityArr);
			}
			// 需要向静态文件中进行数组的输出
			StaticDataFile.saveProvinceFile(proArr); // 实现省份数据的保存
			super.print(true);
		} catch (Exception e) {
			e.printStackTrace();
			super.print(false);
		}
	}
	
	public void list() {
		try {
			// 取得全部的Item的信息；
			List<Item> all = this.itemServiceBack.listDetails() ;
			JSONObject allObj = new JSONObject() ;	// 保存完整的操作信息
			JSONObject itemObj = new JSONObject() ;	// 保存所有的Item信息
			JSONArray allArr = new JSONArray() ;
			JSONArray itemArr = new JSONArray() ;
			Iterator<Item> iter = all.iterator() ;
			while (iter.hasNext()) {
				Item item = iter.next() ;
				JSONObject temp = new JSONObject() ;
				temp.put("iid", item.getIid()) ;
				temp.put("title", item.getTitle()) ;
				itemArr.add(temp) ;	// 保存所有的一级栏目信息
				JSONArray subArr = new JSONArray() ;
				Iterator<Subitem> siter = item.getSubitems().iterator() ;
				while (siter.hasNext()) {	// 循环子分类
					Subitem sub = siter.next() ;
					JSONObject stemp = new JSONObject() ;
					stemp.put("sid", sub.getSid()) ;
					stemp.put("title", sub.getTitle()) ;
					subArr.add(stemp) ; 
					// 有了全部的Subitem，随后向文件中保存
					StaticDataFile.saveSubitemFile(item.getIid(), subArr);
				}
				// 将subitem向temp中保存
				temp.put("subitems", subArr) ;	// 保存所有的子分类
				allArr.add(temp) ;
			}
			allObj.put("allItems", allArr) ;
			itemObj.put("allItems", itemArr) ;
			StaticDataFile.saveSingleFile(allObj);
			StaticDataFile.saveItemFile(itemObj);
			super.print(true);
		} catch (Exception e) {
			super.print(false);
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
