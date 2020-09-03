package com.howay.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author howay
 * @since 2020/9/3
 */
@Repository
@Mapper
public interface EssayDao {

	@Insert("insert into essay(title,content,creation_time,update_time,publisher,label) "
			+ "VALUES(#{title},#{content},#{creation_time},#{update_time},#{publisher},#{label})")
	public int insertUser(
		@Param(value = "title") String title,
		@Param(value = "content") String content,
		@Param(value = "creation_time") String creation_time,
		@Param(value = "update_time") String update_time,
		@Param(value = "publisher") int publisher,
		@Param(value = "label") String label
	);
	
}