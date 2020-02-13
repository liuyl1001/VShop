package cn.mldn.vshop.service.back.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.vshop.dao.IProvinceDAO;
import cn.mldn.vshop.pojo.Province;
import cn.mldn.vshop.service.back.IProvinceServiceBack;
@Service
public class ProvinceServiceImpl implements IProvinceServiceBack {
	@Resource
	private IProvinceDAO provinceDAO ;
	@Override
	public List<Province> list() throws Exception {
		List<Province> all = this.provinceDAO.findAll() ;
		// 随后需要将每一个省份对应的城市信息取得
		Iterator<Province> iter = all.iterator() ;
		while (iter.hasNext()) {
			iter.next().getCities().size() ;	// 加载省份对应的城市
		}
		return all;
	}

}
