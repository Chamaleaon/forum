package com.howay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.howay.dao.UserDao;
import com.howay.util.JsonUtil;

/**
 * 用户控制层
 * 
 * @author howay
 * @since 2020/9/2
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
    UserDao userDao ;
	
	/**
	 * 用户注册
	 */
	@CrossOrigin
	@RequestMapping(value = "/register", method = { RequestMethod.POST, RequestMethod.GET })
	public String addUser(@RequestBody JSONObject reqjo) {
		String name = reqjo.getString("name");
		String password = reqjo.getString("password");
		String email = reqjo.getString("email");
		String homePage = reqjo.getString("homePage");
		if(null==name||null==password) {
			JSONObject rejo = JsonUtil.toJSONObject(1001, "名字或密码为必填");
			return rejo.toJSONString();
		}
		userDao.insertUser(name,password,email,homePage);
		JSONObject rejo = JsonUtil.toJSONObject(0, "SUCCESS");
		return rejo.toJSONString();
	}

}
