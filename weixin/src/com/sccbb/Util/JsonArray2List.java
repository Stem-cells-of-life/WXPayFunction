package com.sccbb.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonArray2List {
	
	
//
//	public static List<HashMap<String,String>> getOrder(String jsonArray) {
//		List<HashMap<String,String>>res = new ArrayList<HashMap<String,String>>();
//		if(jsonArray==null||jsonArray.equals("")||!jsonArray.startsWith("[")||!jsonArray.endsWith("]")){
//			return res;
//		}
//		JSONArray jsons = JSONArray.fromObject(jsonArray);
//		JSONObject json = null;
//		HashMap<String,String>map = null;
//		//提交信息字段
//		String id = null;
//		String bs_agreement_0001 = null;
//		String xy_type = null;
//		String bs_fee_0002 = null;
//		String priceselect = null;
//		for(int i=0;i<jsons.size();i++){
//			json = jsons.getJSONObject(i);
//			id = json.getString("id");
//			bs_agreement_0001 = json.getString("bs_agreement_0001");
//			xy_type = json.getString("xy_type");
//			bs_fee_0002 = json.getString("bs_fee_0002");
//			priceselect = json.getString("priceselect");
//			if(bs_agreement_0001!=null&&!"".equals(bs_agreement_0001)&&xy_type!=null&&!"".equals(xy_type)&&
//			   bs_fee_0002!=null&&!"".equals(bs_fee_0002)&&priceselect!=null&&!"".equals(priceselect)){
//				map = new HashMap<String,String>();
//				map.put("id",id);
//				map.put("bs_agreement_0001",bs_agreement_0001);
//				map.put("xy_type",xy_type);
//				map.put("bs_fee_0002",bs_fee_0002);
//				map.put("priceselect",priceselect);
//				res.add(map);
//			}
//		}
//		return res;
//	}
	
	//JOSN转LIST
	public static List<Map<String, String>>  getJsonListByString(String arr ) { //json文件读取到的String转换可以通过key获取value
		  {     
		        JSONArray arry = JSONArray.fromObject(arr);
		        List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
		        for (int i = 0; i < arry.size(); i++)
		        {  
		            JSONObject jsonObject = arry.getJSONObject(i);
		            Map<String, String> map = new HashMap<String, String>();
		            for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();)
		            {
		                String key = (String) iter.next();
		                String value = jsonObject.get(key).toString();
		                map.put(key, value);
		            }
		            rsList.add(map);
		            
		        }
		        return rsList;
		    }
		}
}
