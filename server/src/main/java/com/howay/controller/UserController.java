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
		if (null == name || null == password) {
			res = JsonUtil.toJSONObject(1001, "名字或密码为必填");
			return res.toJSONString();
		}
		userDao.insertUser(name, password, email, homePage);
		res = JsonUtil.toJSONObject(0, "SUCCESS");
		return res.toJSONString();
	}

	/**
	 * 用户登录
	 */
	@CrossOrigin
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String login(String name,String password,HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		
		JSONObject res;
		List<Map<String,Object>> list = userDao.getUserInfo(name);
		if(list.size()>0) {
			if(list.get(0).get("password").equals(password)) {
				//System.out.println(name);
				session.setAttribute("cl_name", name);
				Cookie nameCookie = new Cookie("cl_name",name);
				nameCookie.setPath(request.getContextPath());
				nameCookie.setMaxAge(-1); 
				response.addCookie(nameCookie);
				
				String id = list.get(0).get("u_id").toString();
				session.setAttribute("cl_id", id);
				Cookie idCookie = new Cookie("cl_id",id);
				idCookie.setPath(request.getContextPath());
				idCookie.setMaxAge(-1); 
				response.addCookie(idCookie);
				
				//System.out.println("ok");
				res = JsonUtil.toJSONObject(0, "SUCCESS");
				return res.toJSONString();
			}
		}
		Cookie cookies[] = request.getCookies();
		for (Cookie cookie : cookies) {
			Cookie nc = new Cookie(cookie.getName(),null);
			nc.setMaxAge(0);
			response.addCookie(nc);
		}
		res = JsonUtil.toJSONObject(1002, "用户名或密码错误");
		return res.toJSONString();
		
	}

}
