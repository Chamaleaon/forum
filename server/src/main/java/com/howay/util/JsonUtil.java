package com.howay.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
    
	public static JSONObject toJSONObject(int code,String desc) {
	    JSONObject jo = new JSONObject();
	    jo.put("RE_CODE", code);
	    jo.put("RE_DESC", desc);
	    return jo;
	}
	
	public static JSONArray toJSONArray(List<Map<String,Object>> list){
		JSONArray ja = new JSONArray();
		for(Map<String,Object> map : list ){
			Iterator<String> iterator = map.keySet().iterator();
			JSONObject jo = new JSONObject();
			while(iterator.hasNext()){
				String key = iterator.next();
				jo.put(key, map.get(key));
			}
			ja.add(jo);
		}
		return ja;
	}
}

