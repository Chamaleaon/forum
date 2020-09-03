package com.howay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author howay
 * @since 2020/9/2
 */
@Repository
@Mapper
public interface UserDao {
	
	@Select("select * from user where u_id=#{u_id}")
	public List<Map<String,Object>> getUserInfoById(@Param(value = "u_id") int u_id);
	
	@Select("select * from user where name=#{name}")
	public List<Map<String,Object>> getUserInfo(@Param(value = "name") String name);
	
	@Insert("insert into user(name,password,email,homePage) VALUES(#{name},#{password},#{email},#{homePage})")
	public int insertUser(
		@Param(value = "name") String name,
		@Param(value = "password") String password,
		@Param(value = "email") String email,
		@Param(value = "homePage") String homePage
	);

}
