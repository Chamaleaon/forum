package com.howay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.howay.dao.EssayDao;
import com.howay.dao.FloorDao;
import com.howay.dao.LayerDao;
import com.howay.dao.RelationDao;
import com.howay.util.JsonUtil;

/**
 * 
 * @author howay
 *
 */
@Service
public class RelationServiceImp implements RelationService {

	@Autowired
	RelationDao relationDao;

	@Autowired
	FloorDao floorDao;

	@Autowired
	LayerDao layerDao;

	@Autowired
	EssayDao essayDao;

	@Override
	public int insert(final int recipient_id, final int sender_id, final String r_type, final String s_type,
			final int rt_id, final int st_id, final String time) {
		return relationDao.insert(recipient_id, sender_id, r_type, s_type, rt_id, st_id, time);
	}

	@Override
	public int delete(final String s_type, final int st_id) {
		return relationDao.delete(s_type, st_id);
	}

	@Override
	public String getMessages(int u_id) {
		JSONObject res;
		JSONArray array = new JSONArray();
		List<Map<String,Object>> list = relationDao.getMessages(u_id);
		for(Map<String,Object> relation:list){
			array.add(this.analysisRelation(relation));
		}
		res = JsonUtil.toJSONObject(0, "SUCCESS");
		res.put("RES", array);
		return res.toJSONString();
	}

	@Override
	public String getReplies(int u_id) {
		JSONObject res;
		JSONArray array = new JSONArray();
		List<Map<String,Object>> list = relationDao.getReplies(u_id);
		for(Map<String,Object> relation:list){
			array.add(this.analysisRelation(relation));
		}
		res = JsonUtil.toJSONObject(0, "SUCCESS");
		res.put("RES", array);
		return res.toJSONString();
	}

	public JSONObject analysisRelation(Map<String,Object> relation){
		JSONObject res = new JSONObject();
		String r_type = (String) relation.get("r_type");
		String s_type = (String) relation.get("s_type");
		int rt_id = (int) relation.get("rt_id");
		int st_id = (int) relation.get("st_id");
		
		JSONObject original = this.analysis(r_type, rt_id);
		JSONObject reply = this.analysis(s_type, st_id);
		res.put("original", original);
		res.put("reply", reply);
		
		return res;
	}
	
	public JSONObject analysis(String type,int id){
		JSONObject res = new JSONObject();
		if("FLOOR".equals(type)){
			List<Map<String,Object>> floorList = floorDao.byId(id);
			if(floorList.size()>0){
				res.put("essayId", floorList.get(0).get("essay"));
				res.put("type","FLOOR");
				res.putAll(floorList.get(0));
			}
		}else if("LAYER".equals(type)){
			List<Map<String,Object>> layerList = layerDao.byId(id);
			if(layerList.size()>0){
				int floor = (int) layerList.get(0).get("floor");
				List<Map<String,Object>> list = floorDao.byId(floor);
				res.put("essayId", list.get(0).get("essay"));
				res.put("type","LAYER");
				res.putAll(layerList.get(0));
			}
		}else{
			List<Map<String,Object>> eList = essayDao.byEId(id);
			if(eList.size()>0){
				res.put("essayId", id);
				res.put("type","ESSAY");
				res.putAll(eList.get(0));
			}
		}
		return res;
	}

}
