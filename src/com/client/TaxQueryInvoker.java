package com.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.BaseQueryResInfo;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-4-23
 *
 * 说明
 * @version
 */
public class TaxQueryInvoker implements ServiceInvoker {
	
	private static final Logger log = Logger.getLogger(TaxQueryInvoker.class.getName());

	public Object invokeService(HashMap mmap) {
		
		Object req = mmap.get("Req");
		boolean cmpFlag = false;
		Object res = null;
		//根据入参调用不同服务，比较返回对象，输出不同的结果
		String type = req.getClass().getSimpleName();
		
		if("BaseQueryReqInfo".equals(type)){
			
			BaseQueryReqInfo baseQueryReqInfo = (BaseQueryReqInfo)req;//投保查询入参
			BaseQueryResInfo newRes = (BaseQueryResInfo)mmap.get("Res");//新版车船税处理结果
			String serviceURI = (String)mmap.get("ServiceURI");
			
			//投保查询服务
			try {
				
				//调用新版车船税服务
				//String serviceURI = "http://192.168.1.51:8888/taxcarship/services/TaxQueryService";
				Service service = new ObjectServiceFactory().create(TaxQueryServicePortType.class);
				XFireProxyFactory xf = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
				TaxQueryServicePortType client = (TaxQueryServicePortType)xf.create(service, serviceURI);
				
				BaseQueryResInfo oldRes = client.tarQuery(baseQueryReqInfo);
				res = oldRes;
				
				//比较新版和旧版车船税服务处理后的结果
				cmpFlag = objectCompare(newRes,oldRes);
				if(cmpFlag){
					log.debug("---------------------------处理结果一致---交易码 = "+newRes.getTaxQueryNo().getTaxDealCode_Type()+"------------------------");
				}else{
					log.debug("---------------------------处理结果不一致---交易码 = "+newRes.getTaxQueryNo().getTaxDealCode_Type()+"------------------------");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		return res;		
	}
	
	private static boolean objectCompare(Object oa, Object ob) throws IllegalArgumentException, ClassNotFoundException, IllegalAccessException, InvocationTargetException{
		
		boolean cmpFlag = false;
		Map<String, Object> map1 = new HashMap();
		Map<String, Object> map2 = new HashMap();
		Map<String, Object> map11 = getObject(oa, map1);
		Map<String, Object> map22 = getObject(ob, map2);
		equ(map11, map22);
		return cmpFlag;
		
	}
	
	/**
	 * 获取得对象的属性和对应的值
	 * @param object
	 * @param map
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Map<String, Object> getObject(Object object, Map<String, Object> map)
			throws ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {

		Method[] model = object.getClass().getDeclaredMethods();
		for (Method m : model) {
			if (m.getName().startsWith("get")) {
				Object param = m.invoke(object);
				if (param == null || "".equals(param.toString())) {
					map.put((m.getName().substring(3, m.getName().length())).toLowerCase(), param);
				} else {
					if (param instanceof Integer) {
						Integer ite = (Integer) param;
						map.put((m.getName().substring(3, m.getName().length())).toLowerCase(), ite);
					} else if (param instanceof String) {
						String str = (String) param;
						map.put((m.getName().substring(3, m.getName().length())).toLowerCase(), str);
					} else if (param instanceof Double) {
						Double duo = (Double) param;
						map.put((m.getName().substring(3, m.getName().length())).toLowerCase(), duo);
					} else if (param instanceof Float) {
						Float flo = (Float) param;
						map.put((m.getName().substring(3, m.getName().length())).toLowerCase(), flo);
					} else if (param instanceof Long) {
						Long lon = (Long) param;
						map.put((m.getName().substring(3, m.getName().length())).toLowerCase(), lon);
					} else if (param instanceof Boolean) {
						Boolean boo = (Boolean) param;
						map.put((m.getName().substring(3, m.getName().length())).toLowerCase(), boo);
					} else if (param instanceof Date) {
						Date date = (Date) param;
						map.put((m.getName().substring(3, m.getName().length())).toLowerCase(), date);
					} else {
						getObject(param, map);
					}
				}
			}
		}
		return map;
	}
   
	/**
	 * 比较
	 * @param map_n
	 * @param map_o
	 */
	public static boolean equ(Map<String, Object> map_n, Map<String, Object> map_o) {
		boolean equFlag = true;
		List<String> list = new ArrayList<String>();
		int a = map_n.size();
		Set<String> set = map_n.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			list.add(it.next().toLowerCase());
		}
		for (int i = 0; i < a; i++) {
			if (map_n.containsKey(list.get(i))
					&& map_o.containsKey(list.get(i))) {
				Object obj1 = map_n.get(list.get(i));
				Object obj2 = map_o.get(list.get(i));
				if (obj1 instanceof Integer) {
					Integer ing1 = (Integer) obj1;
					Integer ing2 = (Integer) obj2;
					if (!ing1.equals(ing2)) {
						equFlag = false;
						log.error("属性名:" + list.get(i) + " "+ "obj1的值:" + " " + obj1 + " " + "obj2的值:"+ " " + obj2);
					}
				} else if (obj1 instanceof String) {
					String ing1 = (String) obj1;
					String ing2 = (String) obj2;
					if (!ing1.equals(ing2)) {
						equFlag = false;
						log.error("属性名:" + list.get(i) + " "+ "obj1的值:" + " " + obj1 + " " + "obj2的值:"+ " " + obj2);
					}
				} else if (obj1 instanceof Double) {
					Double ing1 = (Double) obj1;
					Double ing2 = (Double) obj2;
					if (ing1.doubleValue() != ing2.doubleValue()) {
						equFlag = false;
						log.error("属性名:" + list.get(i) + " "+ "obj1的值:" + " " + obj1 + " " + "obj2的值:"+ " " + obj2);
					}
				} else if (obj1 instanceof Float) {
					Float ing1 = (Float) obj1;
					Float ing2 = (Float) obj2;
					if (ing1!=ing2) {
						equFlag = false;
						log.error("属性名:" + list.get(i) + " "+ "obj1的值:" + " " + obj1 + " " + "obj2的值:"+ " " + obj2);
					}
				} else if (obj1 instanceof Long) {
					Long ing1 = (Long) obj1;
					Long ing2 = (Long) obj2;
					if (ing1 != ing2) {
						equFlag = false;
						log.error("属性名:" + list.get(i) + " "+ "obj1的值:" + " " + obj1 + " " + "obj2的值:"+ " " + obj2);
					}
				} else if (obj1 instanceof Boolean) {
					Boolean ing1 = (Boolean) obj1;
					Boolean ing2 = (Boolean) obj2;
					if (ing1 != ing2) {
						equFlag = false;
						log.error("属性名:" + list.get(i) + " "+ "obj1的值:" + " " + obj1 + " " + "obj2的值:"+ " " + obj2);
					}
				} else if (obj1 instanceof Date) {
					Date ing1 = (Date) obj1;
					Date ing2 = (Date) obj2;
					if (ing1.after(ing2)||ing1.before(ing2)) {
						equFlag = false;
						log.error("属性名:" + list.get(i) + " "+ "obj1的值:" + " " + obj1 + " " + "obj2的值:"+ " " + obj2);
					}
				}
			}
		}
		
		return equFlag;

	}
	
}
