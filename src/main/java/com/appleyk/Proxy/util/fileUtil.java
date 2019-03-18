package com.appleyk.Proxy.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fileUtil {
	public static Map<String, String> fileOp(String filePath) throws InterruptedException {

		Map<String, String> map = new HashMap<>();
		List<String> tempList = new ArrayList<>();

		try (FileReader reader = new FileReader(filePath); BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
		) {
			String line;

			// 网友推荐更加简洁的写法
			while ((line = br.readLine()) != null) {
				// 一次读入一行数据

				char c = line.charAt(0);
				if (charUtil.check(line))
					tempList.add(line);
				else
					continue;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("已读取命令：" + tempList.get(tempList.size() - 1));
		System.out.println("正在进行解析......");
		tempList.remove(tempList.size() - 1);
		for (int i = 0; i < tempList.size(); i++) {
			String tString = tempList.get(i);
			map.put(tString.split(":")[0], charUtil.removeNonLetters(tString.split(":")[1]));
		}

//		System.out.println(tString.split(":")[0]);

//		System.out.println(map);
//		System.out.println(map.size());
		if (map.size() == 3) {
			map.put("attribute", "none");
		}
		System.out.println("解析结果如下：");
		charUtil.outPutInfo(map);
		return map;

	}

}
