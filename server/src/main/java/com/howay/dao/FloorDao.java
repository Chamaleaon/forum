package com.howay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	
	@Select("select * from floor where f_id=#{f_id} and publisher=#{publisher}")
	public List<Map<String,Object>> findByIdAndPublisher(
			@Param(value = "f_id") int f_id,
			@Param(value = "publisher") int publisher);
	
	@Insert("insert into floor(essay,content,creation_time,update_time,publisher,level,info) "
			+ "VALUES(#{essay},#{content},#{creation_time},#{update_time},#{publisher},#{level},#{info})")
	public int insert(
		@Param(value = "essay") int essay,
		@Param(value = "content") String content,
		@Param(value = "creation_time") String creation_time,
		@Param(value = "update_time") String update_time,
		@Param(value = "publisher") int publisher,
		@Param(value = "level") int level,
		@Param(value = "info") String info
	);
	
	@Select("SELECT @@IDENTITY")
	public int getNewId();  //返回insert时最新id
	
	@Delete("delete from floor where f_id=#{f_id};")
	public int delete(@Param(value = "f_id") int f_id);
	
	@Delete("delete from floor where essay=#{essay};")
	public int deleteByEssay(@Param(value = "essay") int essay);

	@Update("update floor set support=#{support},oppose=#{oppose}, where f_id=#{f_id}")
	public int update(
		@Param(value = "f_id") int f_id,
		@Param(value = "support") int support,
		@Param(value = "oppose") int oppose
	);

}
