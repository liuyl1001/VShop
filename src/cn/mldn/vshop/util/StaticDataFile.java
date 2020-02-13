package cn.mldn.vshop.util;

import java.io.FileOutputStream;
import java.io.PrintStream;

import org.apache.struts2.ServletActionContext;

public class StaticDataFile {
	private static final String DIR = "/static-txt/" ;
	
	public static void saveSingleFile(Object obj) {
		saveFileContent(obj,"front/item-all.txt") ;
	}
	
	private static void saveFileContent(Object obj,String fileName) {	// 保存单独文件信息
		PrintStream out = null ;
		try {
			String filePath = ServletActionContext.getServletContext()
					.getRealPath(DIR) + fileName;
			out = new PrintStream(new FileOutputStream(filePath));
			out.print(obj);
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			if (out != null) {
				out.close() ;
			}
		}
	}
	public static void saveItemFile(Object obj) { 
		saveFileContent(obj,"back/item.txt") ;
	}
	public static void saveGoodsFile(Object obj) { 
		saveFileContent(obj,"front/goods/goods.txt") ;
	}
	public static void saveSubitemFile(int iid,Object obj) {
		saveFileContent(obj,"back/subitem-"+iid+".txt") ;
	}
	public static void saveProvinceFile(Object obj) {
		saveFileContent(obj,"front/city/province.txt") ;
	}
	
	public static void saveCityFile(int pid,Object obj) {
		saveFileContent(obj,"front/city/city-"+pid+".txt") ;
	}
}
