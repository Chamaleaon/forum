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
 * 一级评论、层主发言控制层
* 
* @author howay
* @since 2020/9/3
*/

@RestController
@RequestMapping(value = "/floor")
public class FloorController {
	
	@Autowired
	EssayDao essayDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	FloorDao floorDao;
	
	@Autowired
	LayerDao layerDao;
	
	@Autowired
	RelationService relationService;
	/**
	 * 写评论
	 */
	@CrossOrigin
	@RequestMapping(value = "/write", method = { RequestMethod.POST })
	public String write(@RequestBody JSONObject req) {
		JSONObject res;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = formatter.format(date);
		
		int essay = req.getIntValue("essay");
		String content = req.getString("content");
		int publisher = req.getIntValue("publisher");
		int level = req.getIntValue("level");
		String info = req.getString("info");
		System.out.println("*****:"+publisher);
		List<Map<String,Object>> list = userDao.getUserInfoById(publisher);
		List<Map<String,Object>> list2 = essayDao.byEId(essay);
		if(list.size()>0 && list2.size()>0) { //用户存在且文章存在
			floorDao.insert(essay, content, time, time, publisher, level,info);
			int f_id = floorDao.getNewId();
			int sender_id = (int) list2.get(0).get("publisher");
			relationService.insert(sender_id,publisher,"ESSAY","FLOOR",essay, f_id, time);
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			return res.toJSONString();
		}
		res = JsonUtil.toJSONObject(1002, "用户或者文章不存在");
		return res.toJSONString();
	}
	
	//查找一级评论下的全部二级评论
	@CrossOrigin
	@RequestMapping(value = "/find", method = { RequestMethod.POST })
	public JSONArray find(@RequestBody JSONObject req) {
		int f_id = req.getIntValue("id");
		List<Map<String,Object>> list = layerDao.findByFloorID(f_id);
		JSONArray res = JsonUtil.toJSONArray(list);
		return res;
	}
	
	/**
	 * 删除
	 * 同时删除该楼下所有二级回复
	 */
	@CrossOrigin
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public String delete(@RequestBody JSONObject req) {
		JSONObject res;
		int f_id = req.getIntValue("f_id");
		int publisher = req.getIntValue("opretor");
		
		List<Map<String,Object>> list = floorDao.findByIdAndPublisher(f_id,publisher);
		if(list.size()>0) {
			floorDao.delete(f_id);
			relationService.delete("FLOOR", f_id);
			List<Map<String,Object>> layerList = layerDao.findByFloorID(f_id);
			for(Map<String,Object> layer:layerList){
				int l_id = (int) layer.get("l_id");
				relationService.delete("LAYER", l_id);
			}
			layerDao.deleteByFloor(f_id);
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			return res.toJSONString();
		}
		
		res = JsonUtil.toJSONObject(1003, "删除失败");
		return res.toJSONString();
	}
}
