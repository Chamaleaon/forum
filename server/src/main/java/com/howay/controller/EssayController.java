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
import com.howay.dao.UserDao;
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
	
	/**
	 * 写贴子
	 */
	@CrossOrigin
	@RequestMapping(value = "/writePost", method = { RequestMethod.POST })
	public String writePost(@RequestBody JSONObject req) {
		JSONObject res;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = formatter.format(date);
		
		String title = req.getString("title");
		String content = req.getString("content");
		int publisher = req.getIntValue("publisher");
		String label = req.getString("label");
		List<Map<String,Object>> list = userDao.getUserInfoById(publisher);
		if(list.size()>0) { //用户存在
			essayDao.insertUser(title, content, time, time, publisher, label);
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
		List<Map<String,Object>> list = essayDao.getAll();
		JSONArray ja = JsonUtil.toJSONArray(list);
		JSONObject res = JsonUtil.toJSONObject(0, "SUCCESS");
		res.put("RES", ja);
		return res.toJSONString();
	}
	
	/**
	  * 查找所有贴子
	 */
	@CrossOrigin
	@RequestMapping(value = "/byId", method = { RequestMethod.POST })
	public String getByID(@RequestBody JSONObject req) {
		List<Map<String,Object>> list = essayDao.byId(req.getIntValue("id"));
		JSONArray ja = JsonUtil.toJSONArray(list);
		JSONObject res = JsonUtil.toJSONObject(0, "SUCCESS");
		res.put("RES", ja);
		return res.toJSONString();
	}

}
