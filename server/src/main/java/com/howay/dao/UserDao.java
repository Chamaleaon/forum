package com.howay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author howay
 * @since 2020/9/2
 */
@Repository
@Mapper
public interface UserDao {
	
	@Select("select name,email,homePage,avatar,authority,privacy,diary_privacy,info from user where u_id=#{u_id}")
	public List<Map<String,Object>> getUserInfoById(@Param(value = "u_id") int u_id);
	
	@Select("select * from user where name=#{name}")
	public List<Map<String,Object>> getUserInfo(@Param(value = "name") String name);
	
	@Insert("insert into user(name,password,email,homePage,avatar,info) VALUES(#{name},#{password},#{email},#{homePage},#{avatar},#{info})")
	public int insertUser(
		@Param(value = "name") String name,
		@Param(value = "password") String password,
		@Param(value = "email") String email,
		@Param(value = "homePage") String homePage,
		@Param(value = "avatar") String avatar,
		@Param(value = "info") String info
 	);

	@Update("update user set password=#{password} where u_id=#{u_id}")
	public int updatePassword(
		@Param(value = "u_id") int u_id,
		@Param(value = "password") String password
	);

	@Update("update user set name=#{name},email=#{email},homePage=#{homePage},avatar=#{avatar},info=#{info},privacy=#{privacy},diary_privacy=#{diary_privacy} where u_id=#{u_id}")
	public int updateUser(
		@Param(value = "u_id") int u_id,
		@Param(value = "name") String name,
		@Param(value = "email") String email,
		@Param(value = "homePage") String homePage,
		@Param(value = "avatar") String avatar,
		@Param(value = "info") String info,
		@Param(value = "privacy") String privacy,
		@Param(value = "diary_privacy") String diary_privacy
	);

}
