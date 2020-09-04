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
 * @since 2020/9/3
 */
@Repository
@Mapper
public interface FloorDao {
	
	@Select("select a.*,b.name as publisher_name from floor a,user b where f_id=#{f_id} and a.publisher=b.u_id")
	public List<Map<String,Object>> byId(@Param(value = "f_id") int f_id);
	
	@Select("select a.*,b.name as publisher_name from floor a,user b where essay=#{essay} and a.publisher=b.u_id")
	public List<Map<String,Object>> findByEID(@Param(value = "essay") int essay);
	
	@Insert("insert into floor(essay,content,creation_time,update_time,publisher,level) "
			+ "VALUES(#{essay},#{content},#{creation_time},#{update_time},#{publisher},#{level})")
	public int insert(
		@Param(value = "essay") int essay,
		@Param(value = "content") String content,
		@Param(value = "creation_time") String creation_time,
		@Param(value = "update_time") String update_time,
		@Param(value = "publisher") int publisher,
		@Param(value = "level") int level
	);

}
