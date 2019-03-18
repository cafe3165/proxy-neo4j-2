package com.appleyk.Proxy.virtualObejct.GenRTModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.virtualObejct.User;
import com.appleyk.Proxy.virtualObejct.Users;
import com.appleyk.Proxy.virtualObejct.init.initConcept;

public class genUser {
	
	public static void genU(Map<String, String> locIdNameMap,Map<String, Object> userMap,Map<String, String> userIdNameMap,Users users) {
		
		String UName1 = "Jack";
		String UId1 = "U1";
		String ULName1 = "bedroom";
		List<String> uclist1 = new ArrayList<>();
		uclist1.add("C11");

		String UName2 = "Ben";
		String UId2 = "U2";
		String ULName2 = "sittingroom";
		List<String> uclist2 = new ArrayList<>();
		uclist2.add("C21");
		
		User u1 = new User();
		User u2 = new User();
		u1 = (User) initConcept.initUser(UName1, ULName1, UId1, u1, locIdNameMap, uclist1);
		u2 = (User) initConcept.initUser(UName2, ULName2, UId2, u2, locIdNameMap, uclist2);
		
		userMap.put(u1.getUId(), u1);
		userMap.put(u2.getUId(), u2);
		userIdNameMap.put(UName1, UId1);
		userIdNameMap.put(UName2, UId2);

		 
		users.addlist(u1.getUId());
		users.addlist(u2.getUId());
//		列出用户的属性
//		for (String uid : UList) {
//			User tempUser = (User) userMap.get(uid);
//			users.ListProperties(tempUser.getUId(), userMap);
//		}
	}

}
