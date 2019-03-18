package com.appleyk.Proxy.virtualObejct.GenRTModel;

import com.appleyk.Proxy.map.findUtil;
import com.appleyk.Proxy.virtualObejct.Service;
import com.appleyk.Proxy.virtualObejct.init.initConcept;

public class genService {
	
	public static void genS() {
		String ServiceId = "S11";
		String DName = "Gree";
		String CType = "temperature";
		String Effect = "Reduce";
		String DeviceId = findUtil.findUnderid(gree.hashCode(), idmaps);
		String RutimeDeviceId = String.valueOf(gree.hashCode());

		String ServiceId2 = "S21";
		String DeviceId2 = findUtil.findUnderid(panasonic.hashCode(), idmaps);
		String RutimeDeviceId2 = String.valueOf(panasonic.hashCode());
		String DName2 = "Panasonic";
		String CType2 = "temperature";
		String Effect2 = "Reduce";

		String ServiceId3 = "S22";
		String DeviceId3 = findUtil.findUnderid(panasonic.hashCode(), idmaps);
		String RutimeDeviceId3 = String.valueOf(panasonic.hashCode());
		String DName3 = "Panasonic";
		String CType3 = "temperature";
		String Effect3 = "Increase";

		String ServiceId4 = "S12";
		String DeviceId4 = findUtil.findUnderid(gree.hashCode(), idmaps);
		String RutimeDeviceId4 = String.valueOf(gree.hashCode());
		String DName4 = "Gree";
		String CType4 = "temperature";
		String Effect4 = "Assign";

		String ServiceId5 = "S23";
		String DeviceId5 = findUtil.findUnderid(panasonic.hashCode(), idmaps);
		String RutimeDeviceId5 = String.valueOf(panasonic.hashCode());
		String DName5 = "Panasonic";
		String CType5 = "temperature";
		String Effect5 = "Monitor";

		String ServiceId6 = "S13";
		String DeviceId6 = findUtil.findUnderid(gree.hashCode(), idmaps);
		String RutimeDeviceId6 = String.valueOf(gree.hashCode());
		String DName6 = "Gree";
		String CType6 = "temperature";
		String Effect6 = "Increase";

		String ServiceId7 = "S14";
		String DeviceId7 = findUtil.findUnderid(gree.hashCode(), idmaps);
		String RutimeDeviceId7 = String.valueOf(gree.hashCode());
		String DName7 = "Gree";
		String CType7 = "temperature";
		String Effect7 = "Monitor";

		Service coolService = new Service();
		Service coolS = (Service) initConcept.initService(ServiceId, DeviceId, RutimeDeviceId, DName, CType, Effect,
				coolService);

		Service coolService2 = new Service();
		Service coolS2 = (Service) initConcept.initService(ServiceId2, DeviceId2, RutimeDeviceId2, DName2, CType2,
				Effect2, coolService2);

		Service upService3 = new Service();
		Service upS3 = (Service) initConcept.initService(ServiceId3, DeviceId3, RutimeDeviceId3, DName3, CType3,
				Effect3, upService3);

		Service assService = new Service();
		Service assS = (Service) initConcept.initService(ServiceId4, DeviceId4, RutimeDeviceId4, DName4, CType4,
				Effect4, assService);

		Service moniService = new Service();
		Service moniS = (Service) initConcept.initService(ServiceId5, DeviceId5, RutimeDeviceId5, DName5, CType5,
				Effect5, moniService);

		Service upsService4 = new Service();
		Service upS4 = (Service) initConcept.initService(ServiceId6, DeviceId6, RutimeDeviceId6, DName6, CType6,
				Effect6, upsService4);

		Service moniService2 = new Service();
		Service moniS2 = (Service) initConcept.initService(ServiceId7, DeviceId7, RutimeDeviceId7, DName7, CType7,
				Effect7, moniService2);

//		服务配置
		initConcept.serConfig(ndAirCondition, coolS, SerDevMaps, serMap);
		initConcept.serConfig(ndAirCondition, assS, SerDevMaps, serMap);
		initConcept.serConfig(ndAirCondition, upS4, SerDevMaps, serMap);
		initConcept.serConfig(ndAirCondition, moniS2, SerDevMaps, serMap);
		initConcept.serConfig(panasonic, coolS2, SerDevMaps, serMap);
		initConcept.serConfig(panasonic, upS3, SerDevMaps, serMap);
		initConcept.serConfig(panasonic, moniS, SerDevMaps, serMap);
		
	}

}
