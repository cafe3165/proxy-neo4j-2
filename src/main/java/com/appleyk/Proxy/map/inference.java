package com.appleyk.Proxy.map;

import java.util.Map;

import com.appleyk.Proxy.virtualObejct.Context;
import com.appleyk.Proxy.virtualObejct.Contexts;
import com.appleyk.Proxy.virtualObejct.Service;
import com.appleyk.Proxy.virtualObejct.Services;

public class inference {
	public static void changeContext(Services services, Contexts contexts,Map<String, Object> serMap,Map<String, Object> contMap) {
//		services.list();
//		contexts.list();

		for (String sid : services.list(false)) {
			Service service = (Service) services.ListProperties(sid, serMap, false);

			for (String cid : contexts.list(false)) {
				Context context = (Context) contexts.ListProperties(cid, contMap, false);

				if (service.getLName().equals(context.getLName()) && service.getCType().equals(context.getCType())) {

					if ((service.getEffect().equals("Increase") || service.getEffect().equals("Reduce")
							|| service.getEffect().equals("Assign")) && service.getStatus().equals("on")) {
						context.setCValue(service.getSValue());
//						System.out.println(service.getServiceId() + context.getCId());
						context.setCValue(service.getSValue());

					}

				}

			}
		}

	}

	public static boolean judgeContext(Contexts contexts, Services services,Map<String, Object> serMap,Map<String, Object> contMap) {
		boolean status = true;

		for (String cid : contexts.list(false)) {
			Context context = (Context) contexts.ListProperties(cid, contMap, false);

			if (context.getCValue() > context.getRMax()) {
				System.out.println(context.getCValue());

			}

			if (context.getCValue() < context.getRMin()) {
				System.out.println(context.getCValue());
				for (String sid : services.list(false)) {
					Service service = new Service();
					service = (Service) services.ListProperties(sid, serMap, false);
					if (service.getLName().equals(context.getLName())
							&& service.getCType().equals(context.getCType())) {
						System.out.println(service.getServiceId() + service.getStatus() + service.getEffect());

					}
				}
			}

		}

		return status;
	}

}
