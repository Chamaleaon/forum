package com.howay.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author howay
 * @since 2020/9/2
 */
@Repository
@Mapper
public interface UserDao {
	
	@Insert("insert into user(name,password,email,homePage) VALUES(#{name},#{password},#{email},#{homePage})")
	public int insertUser(
		@Param(value = "name") String name,
		@Param(value = "password") String password,
		@Param(value = "email") String email,
		@Param(value = "homePage") String homePage
	);

}
