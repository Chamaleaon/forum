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
	private String avatar;//头像的url
	private int authority;//权限  0:用户，1：管理员（默认为0，管理员账户只允许数据库中进行修改）
	private String privacy;//隐私：PUBLIC(公开)/PRIVATE(私密)/ONLYFANS(仅关注之人) 默认PUBLIC 是否对自己个人信息保密
	private String diary_privacy;//动态隐私，一键控制所有动态，PUBLIC(公开)/PRIVATE(私密)/ONLYFANS(仅关注之人) 默认PUBLIC
	private String info;//信息，允许前端自定义json
	
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
	
	private void test(){
		System.out.println("******test******");
	}
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getDiary_privacy() {
		return diary_privacy;
	}

	public void setDiary_privacy(String diary_privacy) {
		this.diary_privacy = diary_privacy;
	}

	public static void main(String[] args) {
		User user = Builder.of(User::new)  //构建器用法
				.with(User::setU_id, 1)
				.with(User::setName, "zs")
				.with(User::setHomePage, "www.howay.site")
				.with(User::setEmail, "35@qq.com")
				.build();
		System.out.println(user.getName());
		user.test();
	}

	

	
	

}
