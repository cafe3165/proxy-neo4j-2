package com.appleyk.Proxy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class sleepUtil {
	
	public static void Sleep() throws InterruptedException {
		Thread.currentThread();
		Thread.sleep(0);
	}
	public static void outputTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		System.out.println(df.format(new Date()));
	}

}
