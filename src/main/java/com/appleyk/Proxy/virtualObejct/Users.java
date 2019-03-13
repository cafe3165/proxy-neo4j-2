package com.appleyk.Proxy.virtualObejct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Users {
	public List<String> UserIdList = new ArrayList<String>();

	public void addlist(String UId) {
		UserIdList.add(UId);
	}

//	列出所有用戶id
	public List<String> list() {
		System.out.println(UserIdList);
		return UserIdList;

	}

//	列出用戶的所有属性
	public void ListProperties(String UserId, Map<String, Object> userMap) {
		Object user = null;
		for (String lId : userMap.keySet()) {
			if (lId.equals(UserId)) {
				user = userMap.get(lId);
			}
		}

		User u = (User) user;
		System.out.println("UId: " + u.getUId());
		System.out.println("UName: " + u.getUName());
		System.out.println("LName: " + u.getLName());

	}

}
