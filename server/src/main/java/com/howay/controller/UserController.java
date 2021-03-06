package com.howay.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	UserDao userDao;

	/**
	 * 用户注册
	 */
	@CrossOrigin
	@RequestMapping(value = "/register", method = { RequestMethod.POST })
	public String addUser(@RequestBody JSONObject reqjo) {
		JSONObject res;
		String name = reqjo.getString("name");
		String password = reqjo.getString("password");
		String email = reqjo.getString("email");
		String homePage = reqjo.getString("homePage");
		String avatar = reqjo.getString("avatar");
		String info = reqjo.getString("info");
		if (null == name || null == password) {
			res = JsonUtil.toJSONObject(1001, "名字或密码为必填");
			return res.toJSONString();
		}
		userDao.insertUser(name, password, email, homePage,avatar,info);
		res = JsonUtil.toJSONObject(0, "SUCCESS");
		return res.toJSONString();
	}

	/**
	 * 用户登录
	 */
	@CrossOrigin
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String login(String name, String password, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject res;
		List<Map<String, Object>> list = userDao.getUserInfo(name);

		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Allow", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		// 接收跨域的cookie
		response.setHeader("Access-Control-Allow-Credentials", "true");
		if (list.size() > 0) {
			if (list.get(0).get("password").equals(password)) {
				// System.out.println(name);
				int maxAge = -1;
				session.setAttribute("cl_name", name);
				Cookie nameCookie = new Cookie("cl_name", name);
				nameCookie.setPath("/");
				nameCookie.setMaxAge(maxAge);
				response.addCookie(nameCookie);

				String id = list.get(0).get("u_id").toString();
				session.setAttribute("cl_id", id);
				Cookie idCookie = new Cookie("cl_id", id);
				idCookie.setPath("/");
				idCookie.setMaxAge(maxAge);
				response.addCookie(idCookie);

				// System.out.println("ok");
				res = JsonUtil.toJSONObject(0, "SUCCESS");
				return res.toJSONString();
			}
		}
		res = JsonUtil.toJSONObject(1002, "用户名或密码错误");
		return res.toJSONString();
	}

	/**
	 * 根据用户id获取用户信息
	 */
	@CrossOrigin
	@RequestMapping(value = "/info", method = { RequestMethod.POST })
	public String getInfoById(@RequestBody JSONObject req) {
		int u_id = req.getIntValue("u_id");
		JSONObject res;
		List<Map<String, Object>> list = userDao.getUserInfoById(u_id);
		if (list.size() > 0) {
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			for (Map<String, Object> user : list) {
				res.putAll(user);
			}
			return res.toJSONString();
		}
		res = JsonUtil.toJSONObject(1002, "用户不存在");
		return res.toJSONString();
	}

	/**
	 * 用户信息修改
	 */
	@CrossOrigin
	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	public String updateUser(@RequestBody JSONObject req) {
		int u_id = req.getIntValue("u_id");
		String name = req.getString("name");
		String email = req.getString("email");
		String homePage = req.getString("homePage");
		String avatar = req.getString("avatar");
		String info = req.getString("info");
		String privacy = req.getString("privacy");
		String diary_privacy = req.getString("diary_privacy");
		JSONObject res;
		List<Map<String, Object>> list = userDao.getUserInfoById(u_id);
		if (list.size() > 0) {
			if(null==name){
				name = (String) list.get(0).get("name");
			}
			if(null==email){
				email = (String) list.get(0).get("email");
			}
			if(null==homePage){
				homePage = (String) list.get(0).get("homePage");
			}
			if(null==avatar){
				avatar = (String) list.get(0).get("avatar");
			}
			if(null==info){
				info = (String) list.get(0).get("info");
			}
			if(null==privacy){
				privacy = (String) list.get(0).get("privacy");
			}
			if(null==diary_privacy){
				diary_privacy = (String) list.get(0).get("diary_privacy");
			}
			userDao.updateUser(u_id, name, email, homePage, avatar, info,privacy,diary_privacy);
			res = JsonUtil.toJSONObject(0, "SUCCESS");
			return res.toJSONString();
		}
		res = JsonUtil.toJSONObject(1004, "更新失败");
		return res.toJSONString();
	}

	/**
	 * 
	 * 验证用户名是否存在
	 */
	@CrossOrigin
	@RequestMapping(value = "/isExist", method = { RequestMethod.POST })
	public String isExist(@RequestBody JSONObject req) {
		String name = req.getString("name");
		JSONObject res;
		List<Map<String, Object>> list = userDao.getUserInfo(name);
		if(list.size()>0){ //用户名存在
			res = JsonUtil.toJSONObject(1005, "用户名存在");
			return res.toJSONString();
		}
		res = JsonUtil.toJSONObject(0, "用户不存在");
			return res.toJSONString();
	}

}
