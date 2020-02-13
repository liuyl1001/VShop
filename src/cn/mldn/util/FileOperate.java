package cn.mldn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

public class FileOperate {
	/**
	 * 只要有MIME类型就可以实现文件名称的创建，创建的时候使用的是UUID
	 * @param mime 文件类型
	 * @return 动态生成的文件名称
	 */
	public static String createFileName(String mime) {
		String fileExt = null ;	// 创建文件后缀
		switch(mime) {
			case "image/jpeg" : {
				fileExt = "jpg" ;
				break ;
			} 
			case "image/bmp" : {
				fileExt = "bmp" ;
				break ;
			}
			case "image/gif" : {
				fileExt = "gif" ;
				break ;
			}
			case "image/png" : {
				fileExt = "png" ;
				break ;
			}
		}
		return UUID.randomUUID() + "." + fileExt ;
	}
	/**
	 * 实现文件的保存处理操作
	 * @param filePath 文件的保存路径
	 * @param file 包含有具体内容的文件信息
	 * @return 保存成功之后返回false
	 * @throws Exception 如果文件保存失败，或者无法保存将抛出此异常
	 */
	public static boolean saveFile(String filePath,File file) throws Exception {
		// 现在文件操作的完整路径
		String path = ServletActionContext.getServletContext().getRealPath("/") + filePath ;
		File outFile = new File(path) ;	// 定义输出文件
		if (!outFile.getParentFile().exists()) {	// 如果父路径不存在
			outFile.getParentFile().mkdirs() ;	// 创建目录
		}
		InputStream input =  null ;
		OutputStream output = null ;
		try {
			input = new FileInputStream(file); // 输入的源文件
			output = new FileOutputStream(outFile); // 定义输出文件
			byte data [] = new byte [4096] ;
			int temp = 0 ;
			while((temp = input.read(data)) != -1) {
				output.write(data, 0, temp);	// 数据输出
			}
		} catch (Exception e) {
			throw e ;
		} finally {
			if (input != null) {
				input.close(); 
			}
			if (output != null) {
				output.close() ;
			}
		}
		return true ;
	}
	/**
	 * 文件删除处理
	 * @param filePath 要删除文件子路径
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String filePath) {
		String path = ServletActionContext.getServletContext().getRealPath("/") + filePath ;
		File file = new File(path) ;	// 定义删除文件
		if (file.exists()) {
			file.delete() ;	// 删除文件
		}
		return true ; 
	} 
}
