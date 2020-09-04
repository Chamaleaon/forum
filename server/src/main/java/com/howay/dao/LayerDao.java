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
 * @since 2020/9/4
 */
@Repository
@Mapper
public interface LayerDao {
	
	@Select("select m.*,n.name as responder_name from ("
			+ "select a.*,b.name as publisher_name FROM "
			+ "layer a,user b where a.publisher=b.u_id) m,"
			+ "user n where m.responder=n.u_id and m.floor=#{floor}")
	public List<Map<String,Object>> findByFloorID(@Param(value = "floor") int floor);
	
	@Insert("insert into layer(floor,content,creation_time,update_time,publisher,responder,level) "
			+ "VALUES(#{floor},#{content},#{creation_time},#{update_time},#{publisher},#{responder},#{level})")
	public int insert(
		@Param(value = "floor") int floor,
		@Param(value = "content") String content,
		@Param(value = "creation_time") String creation_time,
		@Param(value = "update_time") String update_time,
		@Param(value = "publisher") int publisher,
		@Param(value = "responder") int responder,
		@Param(value = "level") int level
	);

}
