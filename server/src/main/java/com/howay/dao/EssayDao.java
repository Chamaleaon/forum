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
public interface EssayDao {

	@Select("select * from essay")
	public List<Map<String,Object>> getAll();
	
	@Select("select * from essay where publisher=#{u_id}")
	public List<Map<String,Object>> byId(@Param(value = "u_id") int u_id);
	
	@Select("select * from essay where e_id=#{e_id}")
	public List<Map<String,Object>> byEId(@Param(value = "e_id") int e_id);
	
	@Insert("insert into essay(title,content,creation_time,update_time,publisher,label) "
			+ "VALUES(#{title},#{content},#{creation_time},#{update_time},#{publisher},#{label})")
	public int insert(
		@Param(value = "title") String title,
		@Param(value = "content") String content,
		@Param(value = "creation_time") String creation_time,
		@Param(value = "update_time") String update_time,
		@Param(value = "publisher") int publisher,
		@Param(value = "label") String label
	);
	
}
