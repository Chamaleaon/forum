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
import com.howay.dao.EssayDao;
import com.howay.dao.FloorDao;
import com.howay.dao.UserDao;
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
	/**
	 * 写评论
	 */
	@CrossOrigin
	@RequestMapping(value = "/write", method = { RequestMethod.POST })
	public String writePost(@RequestBody JSONObject req) {
		JSONObject res;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = formatter.format(date);
		
		int essay = req.getIntValue("essay");
		String content = req.getString("content");
		int publisher = req.getIntValue("publisher");
		int level = req.getIntValue("level");
		List<Map<String,Object>> list = userDao.getUserInfoById(publisher);
		List<Map<String,Object>> list2 = essayDao.byEId(essay);
		if(list.size()>0 && list2.size()>0) { //用户存在且文章存在
			floorDao.insertUser(essay, content, time, time, publisher, level);
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			return res.toJSONString();
		}
		res = JsonUtil.toJSONObject(1002, "用户或者文章不存在");
		return res.toJSONString();
	}

}
