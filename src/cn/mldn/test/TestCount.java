package cn.mldn.test;

import java.io.File;
import java.util.Scanner;

public class TestCount {
	private static long count = 0 ; 
	public static void main(String[] args) throws Exception {
		String filePath = "E:" + File.separator + "ws2015" + File.separator
				+ "VShop"; //  + File.separator + "src"
		File fileDir = new File(filePath) ;
		stat(fileDir) ;
		System.out.println("总行数：" + count);
	}
	public static void stat(File file) throws Exception {
		if (file.isDirectory()) {	// 文件是目录
			File temp [] = file.listFiles() ;
			for (int x = 0; x < temp.length; x++) {
				stat(temp[x]) ;
			}
		} else {
				System.out.print(file.getName() + "\t\t\t\t，行数：");
				Scanner scan = new Scanner(file) ;
				scan.useDelimiter("\n") ;
				int c = 0 ;
				while(scan.hasNext()) {
					String str = scan.next() ;
					if (!(str.contains("*") || str.contains("//"))) {
						c ++ ;
						count ++ ;
					}
				}
				System.out.println(c);
		}
	}
}
