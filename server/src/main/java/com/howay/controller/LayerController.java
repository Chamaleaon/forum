package com.howay.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.howay.dao.FloorDao;
import com.howay.dao.LayerDao;
import com.howay.dao.UserDao;
import com.howay.service.RelationService;
import com.howay.util.JsonUtil;

/**
 * 二级评论控制层
 * 
 * @author howay
 * @since 2020/9/3
 */

@RestController
@RequestMapping(value = "/layer")
public class LayerController {

	@Autowired
	UserDao userDao;

	@Autowired
	FloorDao floorDao;

	@Autowired
	LayerDao layerDao;
	
	@Autowired
	RelationService relationService;

	/**
	 * 写回复
	 */
	@CrossOrigin
	@RequestMapping(value = "/write", method = { RequestMethod.POST })
	public String write(@RequestBody JSONObject req) {
		JSONObject res;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = formatter.format(date);

		int floor = req.getIntValue("floor");
		String content = req.getString("content");
		int publisher = req.getIntValue("publisher");
		int responder = req.getIntValue("responder");
		int level = req.getIntValue("level");
		int replied_lid = req.getIntValue("replied_lid");
		List<Map<String, Object>> uList = userDao.getUserInfoById(publisher);
		List<Map<String, Object>> uList2 = userDao.getUserInfoById(responder);
		List<Map<String, Object>> list = floorDao.byId(floor);
		boolean isOk = false;
		String r_type = "LAYER";
		int rt_id = replied_lid;
		if(replied_lid==-1) {
			isOk = true;
			r_type = "FLOOR";
			rt_id = floor;
		}else {
			r_type = "LAYER";
			rt_id = replied_lid;
			List<Map<String, Object>> list2 = layerDao.findById(replied_lid);
			if(list2.size()>0) {
				isOk = true;
			}else {
				isOk = false;
			}
		}
		if (list.size() > 0 && isOk && uList.size() > 0 && uList2.size() > 0) { // 用户存在且楼层存在且回复存在
			layerDao.insert(floor, content, time, time, publisher, responder, level, replied_lid);
			int st_id = layerDao.getNewId();
			relationService.insert(responder, publisher, r_type, "LAYER", rt_id, st_id, time);
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			return res.toJSONString();
		}
		res = JsonUtil.toJSONObject(1002, "用户或者回复不存在");
		return res.toJSONString();
	}

	/**
	 * 删除
	 * 同时删除该回复下所有回复
	 */
	@CrossOrigin
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public String delete(@RequestBody JSONObject req) {
		JSONObject res;
		int l_id = req.getIntValue("l_id");
		int publisher = req.getIntValue("opretor");
		List<Map<String, Object>> list = layerDao.findByIdAndPublisher(l_id, publisher);
		if (list.size() > 0) {
			layerDao.delete(l_id, publisher);
			List<Integer> deleteList = new  ArrayList<Integer>();
			deleteList.add(l_id);
			while(true) {  //删除该评论下所有二级回复
				if(deleteList.size()<=0) {
					break;
				}
				int id = deleteList.get(0);
				deleteList.remove(0);
				List<Map<String, Object>> lt = layerDao.findByReplied_lid(id);
				for(Map<String, Object> temp:lt) {
					int lid = (int) temp.get("l_id");
					//System.out.println("删除"+lid);
					deleteList.add(lid);
					layerDao.deleteById(lid);
				}
			}
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			return res.toJSONString();
		}
		res = JsonUtil.toJSONObject(1003, "删除失败");
		return res.toJSONString();
	}

}
