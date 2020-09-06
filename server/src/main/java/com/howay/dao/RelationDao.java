package com.howay.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
	

}
