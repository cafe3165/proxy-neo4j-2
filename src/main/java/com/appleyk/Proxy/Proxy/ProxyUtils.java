
package com.appleyk.Proxy.Proxy;

import java.lang.reflect.Proxy;

/**
 * @author chenshihong02
 * @version 1.0
 * @created 2018/12/23 下午6:39
 **/
public class ProxyUtils {


    //运行时对象的代理
    public static Object getProxy(Object obj) {
    	
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new RuntimeHandler(obj));
    }
}