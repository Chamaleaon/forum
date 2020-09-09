package com.howay.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.howay.dao.EssayDao;
import com.howay.dao.FloorDao;
import com.howay.dao.LayerDao;
import com.howay.dao.UserDao;
import com.howay.service.RelationService;
import com.howay.util.JsonUtil;

/**
 * 贴子控制层
 * 
 * @author howay
 * @since 2020/9/3
 */

@RestController
@RequestMapping(value = "/essay")
public class EssayController {

	@Autowired
	EssayDao essayDao;

	@Autowired
	UserDao userDao;

	@Autowired
	LayerDao layerDao;

	@Autowired
	FloorDao floorDao;

	@Autowired
	RelationService relationService;

	/**
	 * 写贴子
	 */
	@CrossOrigin
	@RequestMapping(value = "/writePost", method = { RequestMethod.POST })
	public String writePost(@RequestBody JSONObject req) {
		JSONObject res;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = formatter.format(date);

		String title = req.getString("title");
		String content = req.getString("content");
		int publisher = req.getIntValue("publisher");
		String label = req.getString("label");
		List<Map<String, Object>> list = userDao.getUserInfoById(publisher);
		if (list.size() > 0) { // 用户存在
			essayDao.insert(title, content, time, time, publisher, label);
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			return res.toJSONString();
		}
		res = JsonUtil.toJSONObject(1002, "用户不存在");
		return res.toJSONString();
	}

	/**
	 * 查找所有贴子
	 */
	@CrossOrigin
	@RequestMapping(value = "/all", method = { RequestMethod.POST })
	public String getAll() {
		List<Map<String, Object>> list = essayDao.getAll();
		JSONArray ja = JsonUtil.toJSONArray(list);
		JSONObject res = JsonUtil.toJSONObject(0, "SUCCESS");
		res.put("RES", ja);
		return res.toJSONString();
	}

	/**
	 * 通过用户id查找贴子
	 */
	@CrossOrigin
	@RequestMapping(value = "/byId", method = { RequestMethod.POST })
	public String getByID(@RequestBody JSONObject req) {
		List<Map<String, Object>> list = essayDao.byId(req.getIntValue("id"));
		JSONArray ja = JsonUtil.toJSONArray(list);
		JSONObject res = JsonUtil.toJSONObject(0, "SUCCESS");
		res.put("RES", ja);
		return res.toJSONString();
	}

	/**
	 * 查找某个id贴子下的一级评论一级二级评论
	 */
	@CrossOrigin
	@RequestMapping(value = "/find", method = { RequestMethod.POST })
	public String find(@RequestBody JSONObject req) {
		JSONObject res;
		int e_id = req.getIntValue("e_id");
		List<Map<String, Object>> eList = essayDao.byEId(e_id);
		if (eList.size() > 0) {
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			for (Map<String, Object> essay : eList) {
				res.putAll(essay);
			}
			List<Map<String, Object>> floorList = floorDao.findByEID(e_id);
			JSONArray floorJa = new JSONArray();
			for (Map<String, Object> floor : floorList) {
				// System.out.println(floor);
				int f_id = (int) floor.get("f_id");
				List<Map<String, Object>> layerList = layerDao.findByFloorID(f_id);
				JSONObject e = new JSONObject(floor);
				JSONArray layerJa = JsonUtil.toJSONArray(layerList);
				e.put("layer", layerJa);
				floorJa.add(e);
			}
			res.put("floor", floorJa);
			return res.toJSONString();
		}
		res = JsonUtil.toJSONObject(1002, "贴子不存在");
		return res.toJSONString();
	}

	/**
	 * 删除 同时删除该文章下所有回复（包括一级和二级评论）
	 */
	@CrossOrigin
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public String delete(@RequestBody JSONObject req) {
		JSONObject res;
		int e_id = req.getIntValue("e_id");
		int publisher = req.getIntValue("opretor");
		// 通过e_id和publisher判断是否允许删除
		List<Map<String, Object>> list = essayDao.byEidAndPublisher(e_id, publisher);
		if (list.size() > 0) {
			essayDao.delete(e_id);// 删除文章
			List<Map<String, Object>> fList = floorDao.findByEID(e_id);
			for (Map<String, Object> floor : fList) {
				int f_id = (int) floor.get("f_id");
				relationService.delete("FLOOR", f_id);
				List<Map<String,Object>> layerList = layerDao.findByFloorID(f_id);
				for(Map<String,Object> layer:layerList){
					int l_id = (int) layer.get("l_id");
					relationService.delete("LAYER", l_id);
				}
				layerDao.deleteByFloor(f_id);// 删除二级评论
			}
			floorDao.deleteByEssay(e_id); // 删除一级评论
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			return res.toJSONString();
		}

		res = JsonUtil.toJSONObject(1003, "删除失败");
		return res.toJSONString();
	}

	@CrossOrigin
	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	public String update(@RequestBody JSONObject req) {
		JSONObject res;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = formatter.format(date);
		int e_id = req.getIntValue("e_id");
		int publisher = req.getIntValue("opretor");
		String title = req.getString("title");
		String content = req.getString("content");
		//List<Map<String ,Object>> list = essayDao.byEId(e_id);
		List<Map<String, Object>> list = essayDao.byEidAndPublisher(e_id, publisher);
		
		if(list.size()>0) { 
			essayDao.update(e_id, title, content,time);
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			return res.toJSONString();
		}
		res = JsonUtil.toJSONObject(1004, "更新失败");
		return res.toJSONString();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/search", method = { RequestMethod.POST })
	public String search(@RequestBody JSONObject req) {
		String key = req.getString("key");
		List<Map<String, Object>> list = essayDao.search(key);
		JSONArray ja = JsonUtil.toJSONArray(list);
		JSONObject res = JsonUtil.toJSONObject(0, "SUCCESS");
		res.put("RES", ja);
		return res.toJSONString();
	}

}
