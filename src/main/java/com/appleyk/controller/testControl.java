package com.appleyk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appleyk.node.Device;
import com.appleyk.node.Location;
import com.appleyk.node.Service;
import com.appleyk.node.User;
//import com.appleyk.relation.LikeRelation;
import com.appleyk.relation.Provide;
import com.appleyk.repository.DeviceRepository;
//import com.appleyk.repository.LikeRelationRepository;
import com.appleyk.repository.LocationRepository;
import com.ConfigGenerate.ConfigReader;
import com.appleyk.RelationRepository.ProvideRepository;
import com.appleyk.repository.ServiceRepository;
import com.appleyk.repository.UserRepository;

@RestController
public class testControl {
	@Autowired
	UserRepository userRepository;
	@Autowired
	DeviceRepository deviceRepository;
	@Autowired
	ServiceRepository serviceRepository;
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	ProvideRepository provideRepository;
//	@Autowired
//	LikeRelationRepository likeRepository;

	@RequestMapping("/test3")
	public String index() {
		return "Hello World 888";
	}

//	@RequestMapping("/cd")
//	public void creatDevice() {
//		List<Device> devicesNodes = new ArrayList<>();
//		Device dNode1 = new Device();
//		dNode1.setNN("D5");
//		dNode1.setDName("Airconditioner");
//		dNode1.setLName("bedroom");
//		dNode1.setKey("Temperature");
//		dNode1.setValue(22);
//
////		Device dNode2 = new Device();
////		dNode2.setName("Light");
////		dNode2.setDescribe("This is a light.");
////		
//		devicesNodes.add(dNode1);
////		devicesNodes.add(dNode2);			
//		Iterable<Device> iterable = deviceRepository.save(devicesNodes);
//////		User user=userRepository.save(userNode1);
//		for (Device d : iterable) {
//			System.out.println("创建节点：【" + d.getDName() + "】成功！");
//		}
//
//	}

//	@RequestMapping("/cs")
//	public void createService() {
//		Service service = new Service();
//		service.setName("S54");
//		service.setLName("bedroom");
//		service.setCType("Temperature");
//		service.setEffect("Assign");
//		service.setDName("Airconditioner");
//		service.setState("on");
//		service.setValue(22);
//		serviceRepository.save(service);
//
//	}

	@RequestMapping("/cl")
	public void createLocation() {
		ConfigReader dom4jParser = new ConfigReader(new File("Location.xml"));
		List<HashMap<String, String>> lMaps=dom4jParser.traversalDocumentByIterator();
		List<Location> l = new ArrayList<>();
		for (int i = 0; i < lMaps.size(); i++) {
			HashMap<String, String> map=lMaps.get(i);			
			Location location = new Location();
			location.setNN(map.get("NN"));
			location.setLName(map.get("LName"));
			l.add(location);
		}
		locationRepository.save(l);

	}
	
	@RequestMapping("/cuu")
	public void createUser() {
		ConfigReader dom4jParser = new ConfigReader(new File("User.xml"));
		List<HashMap<String, String>> uMaps=dom4jParser.traversalDocumentByIterator();
		List<User> u = new ArrayList<>();
		for (int i = 0; i < uMaps.size(); i++) {
			HashMap<String, String> map=uMaps.get(i);
			User user = new User();
			user.setNN(map.get("NN"));
			user.setLName(map.get("LName"));
			user.setUName(map.get("UName"));
			u.add(user);
		}
		userRepository.save(u);
	}
	
	@RequestMapping("/cdd")
	public void createDevice() {
		ConfigReader dom4jParser = new ConfigReader(new File("Device.xml"));
		List<HashMap<String, String>> dMaps=dom4jParser.traversalDocumentByIterator();
		List<Device> d = new ArrayList<>();
		for (int i = 0; i < dMaps.size(); i++) {
			HashMap<String, String> map=dMaps.get(i);
			Device device = new Device();
			device.setNN(map.get("NN"));
			device.setLName(map.get("LName"));
			device.setDName(map.get("DName"));
			device.setKey(map.get("Key"));
			double Value=Double.valueOf(map.get("Value").toString());
			device.setValue(Value);
			
			d.add(device);
		}
		deviceRepository.save(d);
		
		
	}
	@RequestMapping("ccc")
	public void createContext() {
		ConfigReader dom4jParser = new ConfigReader(new File("Device.xml"));
		List<HashMap<String, String>> cMaps=dom4jParser.traversalDocumentByIterator();
//		List<Device> c = new ArrayList<>();
	}
	
	
	

//	@RequestMapping("/dps")
//	public void createDeviceProvideService() {
//		System.out.println("666");
//		Provide provide = new Provide();
//		Device startNode = new Device();
//		startNode.setDName("Airconditioner");
////
//		Service endNode = new Service();
//		endNode.setDName("Airconditioner");
////
//		provide.setStartNode(startNode);
//		provide.setEndNode(endNode);
//		System.out.println(startNode.getDName());
//		System.out.println(endNode.getDName());
////
//		List<Provide> provides = provideRepository.createLikes(startNode.getDName(), endNode.getDName());
////			
////			/**
////			 * 遍历创建的关系
////			 */
////			for (Provide p : provides) {
////				Device d = (Device) p.getStartNode();
////				Service s = (Service) p.getEndNode();
////				System.out.println(d.getDName() + "--喜欢-->" + s.getName());
////			}
//	}

//	@RequestMapping("/testlike")
//	public void createLikeRelation() throws Exception {
//
//		LikeRelation like = new LikeRelation();
//
//		/**
//		 * 节点 == 刘大壮
//		 */
//		User startNode = new User();
//		startNode.setUid(1004L);
//
//		/**
//		 * 节点 == 马晓丽
//		 */
//		User endNode = new User();
//		endNode.setUid(1003L);
//
//		like.setStartNode(startNode);
//		like.setEndNode(endNode);
//
//		like.setRelationID(520);
//		like.setSince(2018);
//		like.setReason("晓丽是女神");
//
//		List<LikeRelation> likes = likeRepository.createLikes(startNode.getUid(), 
//				endNode.getUid(), like.getRelationID(),like.getSince(),like.getReason());	
//		
//		/**
//		 * 遍历创建的关系
//		 */
//		for (LikeRelation likeRelation : likes) {
//			User sNode = (User) likeRelation.getStartNode();
//			User eNode = (User) likeRelation.getEndNode();
//			System.out.println(sNode.getName() + "--喜欢-->" + eNode.getName());
//		}
//	}
//	
//	

}
