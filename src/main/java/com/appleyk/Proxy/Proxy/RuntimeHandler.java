
package com.appleyk.Proxy.Proxy;

import com.appleyk.Proxy.map.Relation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.io.*;

/**
 * @author chenshihong02
 * @version 1.0
 * @created 2018/12/23 下午6:40
 **/
public class RuntimeHandler implements InvocationHandler {

	// 运行时对象
	private Object obj;

	private final static Class[] baseTypes = { int.class, double.class, float.class, char.class, boolean.class,
			byte.class, short.class, long.class };
	private final static Class[] wrapTypes = { Integer.class, Double.class, Float.class, Character.class, Boolean.class,
			Byte.class, Short.class, Long.class };

	public RuntimeHandler(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getName().equals("hashCode")) {
			return obj.hashCode();
		}
		//输出参数值
		if(args!=null&&args.length>0)
			for(int i=0;i<args.length;i++)
				{
//				System.out.println(args[i]);
				write(args[i],method.getName());
				}
//		String data = " This content will append to the end of the file";
//	    File file =new File("javaio-appendfile.txt");
//	    if(!file.exists()){
//	        file.createNewFile();
//	       }
//
//	       //true = append file
//	    FileWriter fileWritter = new FileWriter(file.getName(),true);
//	    fileWritter.write(data);
//	    fileWritter.close();
	    
		// 获取运行时对象中type的值 来得到对应的底层设备名
		Field type = obj.getClass().getDeclaredField("type");
		
		type.setAccessible(true);
		String deviceTypeName = (String) type.get(obj);

		// 运行时对象方法调用 映射到 底层设备的api
		Object o=null;
		for (String k : Relation.apiMaps.keySet()) {
//			System.out.println(method.getDeclaringClass().getName());
			if (k.equals(method.getDeclaringClass().getName() + "." + method.getName())) {
				List<String> candidateMethods = Relation.apiMaps.get(k);
				for (int i = 0; i < candidateMethods.size(); i++) {
					if (candidateMethods.get(i).contains(deviceTypeName)) {
						Class<?> deviceType = Class.forName(
								candidateMethods.get(i).substring(0, candidateMethods.get(i).lastIndexOf(".")));
						// Method deviceMethod = getMethod(deviceType,
						// candidateMethods.get(i).substring(candidateMethods.get(i).lastIndexOf(".") +
						// 1), args);
						Class<?>[] parameterTypes = method.getParameterTypes();
						
						Method deviceMethod = deviceType.getDeclaredMethod(
								candidateMethods.get(i).substring(candidateMethods.get(i).lastIndexOf(".") + 1),
								parameterTypes);

						// 通过运行时对象代理得到底层设备对象
						Object deviceObject = Relation.objMaps.get(proxy);
//						o=deviceObject;
//						System.out.println(deviceObject);
//						System.out.println(o);
						// 调用底层设备对象
						
						return deviceMethod.invoke(deviceObject, args);
					}
				}
			}
		}

		return o;
	}
	
	public static void write(Object o,String MName) throws IOException {
//		System.out.println(o);
		String data =o.toString();
	    File file =new File(MName+".txt");
	    if(!file.exists()){
	        file.createNewFile();
	       }
	    
	       //true = append file
	    FileWriter fileWritter = new FileWriter(file.getName(),false);
	    fileWritter.write(data);
	    fileWritter.close();		
	}
	
	
	
	

	private Method getMethod(Class<?> clazz, String methodName, Object[] args) throws Exception {

		if (args == null || args.length == 0) {
			return clazz.getDeclaredMethod(methodName);
		}

		Class[] argsTypes = new Class[args.length];
		// 获取参数类型
		for (int j = 0; j < argsTypes.length; j++) {
			argsTypes[j] = args[j].getClass();
		}

		// 获取类的所有方法
		Method[] methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methodName.equals(methods[i].getName())) {
				Class[] candidateArgsTypes = methods[i].getParameterTypes();
				boolean find = true;
				for (int k = 0; k < candidateArgsTypes.length; k++) {
					if (!isMatch(candidateArgsTypes[k], argsTypes[k])) {
						find = false;
						break;
					}
				}
				if (find) {
					return methods[i];
				}
			}
		}
		return null;
	}

	/**
	 *
	 * @param c 形参类型
	 * @param t 实参类型
	 * @return
	 */
	private boolean isMatch(Class c, Class t) {
		// 形参 实参类型一样
		if (c == t) {
			return true;
		}
		// 实参是形参的子类
		if (t.getSuperclass() == c) {
			return true;
		}
		// 实参实现了形参的接口
		Class[] interfaces = t.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			if (c == interfaces[i]) {
				return true;
			}
		}

		// 实参是包装类型 形参是基本类型
		for (int i = 0; i < baseTypes.length; i++) {
			if (c == baseTypes[i] && t == wrapTypes[i]) {
				return true;
			}
		}

		return false;
	}
}