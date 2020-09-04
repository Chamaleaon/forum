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

import com.alibaba.fastjson.JSONObject;
import com.howay.dao.FloorDao;
import com.howay.dao.LayerDao;
import com.howay.dao.UserDao;
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

	/**
	 * 写回复
	 */
	@CrossOrigin
	@RequestMapping(value = "/write", method = { RequestMethod.POST })
	public String writePost(@RequestBody JSONObject req) {
		JSONObject res;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = formatter.format(date);

		int floor = req.getIntValue("floor");
		String content = req.getString("content");
		int publisher = req.getIntValue("publisher");
		int responder = req.getIntValue("responder");
		int level = req.getIntValue("level");
		List<Map<String, Object>> uList = userDao.getUserInfoById(publisher);
		List<Map<String, Object>> uList2 = userDao.getUserInfoById(responder);
		List<Map<String, Object>> list = floorDao.byId(floor);

		if (list.size() > 0 && uList.size() > 0 && uList2.size() > 0) { // 用户存在且楼层存在
			layerDao.insert(floor, content, time, time, publisher, responder, level);
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			return res.toJSONString();
		}
		res = JsonUtil.toJSONObject(1002, "用户或者文章不存在");
		return res.toJSONString();
	}

}
