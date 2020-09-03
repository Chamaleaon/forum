package com.howay.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.howay.util.Builder;
/**
  * 用户类
 * 
 * @author howay
 * @since 2020/9/2
 */

@Entity
@Table(name="user")
public class User {
	
	private int u_id;  //id pk nn
	private String name; //nn(不能为空)
	private String password; //nn
	private String email;
	private String homePage; //主页
	
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void main(String[] args) {
		User user = Builder.of(User::new)  //构建器用法
				.with(User::setU_id, 1)
				.with(User::setName, "zs")
				.with(User::setHomePage, "www.howay.site")
				.with(User::setEmail, "35@qq.com")
				.build();
		System.out.println(user.getName());
	}
	

}
