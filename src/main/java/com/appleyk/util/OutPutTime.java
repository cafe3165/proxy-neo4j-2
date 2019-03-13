package com.appleyk.util;
import java.util.Date;
import java.text.SimpleDateFormat;

public class OutPutTime {
	
	public static void outPutTime() {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	     System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	}
	
}
