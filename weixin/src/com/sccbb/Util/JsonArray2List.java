package com.sccbb.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonArray2List {
	
	
	public static List<HashMap<String,String>> getOrder(String jsonArray) {
		List<HashMap<String,String>>res = new ArrayList<HashMap<String,String>>();
		if(jsonArray==null||jsonArray.equals("")||!jsonArray.startsWith("[")||!jsonArray.endsWith("]")){
			return res;
		}
		JSONArray jsons = JSONArray.fromObject(jsonArray);
		JSONObject json = null;
		HashMap<String,String>map = null;
		//提交信息字段
		String bs_agreement_0001 = null;
		String xy_type = null;
		String bs_fee_0002 = null;
		String dcf = null;
		String isisvalid = null;
		String priceselect = null;
		for(int i=0;i<jsons.size();i++){
			json = jsons.getJSONObject(i);
			bs_agreement_0001 = json.getString("bs_agreement_0001");
			xy_type = json.getString("xy_type");
			bs_fee_0002 = json.getString("bs_fee_0002");
			dcf = json.getString("dcf");
			isisvalid = json.getString("isisvalid");
			priceselect = json.getString("priceselect");
			if(bs_agreement_0001!=null&&!"".equals(bs_agreement_0001)&&xy_type!=null&&!"".equals(xy_type)&&
			   bs_fee_0002!=null&&!"".equals(bs_fee_0002)&&dcf!=null&&!"".equals(dcf)&&
			   isisvalid!=null&&!"".equals(isisvalid)&&priceselect!=null&&!"".equals(priceselect)){
				map = new HashMap<String,String>();
				map.put("bs_agreement_0001",bs_agreement_0001);
				map.put("xy_type",xy_type);
				map.put("bs_fee_0002",bs_fee_0002);
				map.put("dcf",dcf);
				map.put("isisvalid",isisvalid);
				map.put("priceselect",priceselect);
				res.add(map);
			}
		}
		return res;
	}
}
