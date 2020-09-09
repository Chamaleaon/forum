package com.howay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author howay
 * @since 2020/9/6
 */
@Repository
@Mapper
public interface RelationDao {
	
	@Insert("insert into relation(recipient_id,sender_id,r_type,s_type,rt_id,st_id,time) "
			+ "VALUES(#{recipient_id},#{sender_id},#{r_type},#{s_type},#{rt_id},#{st_id},#{time})")
	public int insert(
		@Param(value = "recipient_id") int recipient_id,
		@Param(value = "sender_id") int sender_id,
		@Param(value = "r_type") String r_type,
		@Param(value = "s_type") String s_type,
		@Param(value = "rt_id") int rt_id,
		@Param(value = "st_id") int st_id,
		@Param(value = "time") String time
	);

	//根据发送人的发送的类型已经id进行删除
	@Delete("delete from relation where s_type=#{s_type} and st_id=#{st_id}")
	public int delete(@Param(value = "s_type") String s_type,@Param(value = "st_id") int st_id);
	
	@Select("select * from relation where recipient_id=#{u_id}")
	public List<Map<String,Object>> getMessages(@Param(value = "u_id") int u_id);

	@Select("select * from relation where sender_id=#{u_id}")
	public List<Map<String,Object>> getReplies(@Param(value = "u_id") int u_id);

}
