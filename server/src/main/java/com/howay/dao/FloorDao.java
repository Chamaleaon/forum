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
public interface FloorDao {
	
	@Insert("insert into floor(essay,content,creation_time,update_time,publisher,level) "
			+ "VALUES(#{essay},#{content},#{creation_time},#{update_time},#{publisher},#{level})")
	public int insertUser(
		@Param(value = "essay") int essay,
		@Param(value = "content") String content,
		@Param(value = "creation_time") String creation_time,
		@Param(value = "update_time") String update_time,
		@Param(value = "publisher") int publisher,
		@Param(value = "level") int level
	);

}
