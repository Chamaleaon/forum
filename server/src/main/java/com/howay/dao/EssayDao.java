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
public interface EssayDao {

	@Select("select a.*,b.name as publisher_name from essay a,user b where a.publisher=b.u_id and a.type=#{type}")
	public List<Map<String,Object>> getAll(@Param(value = "type") String type);
	
	@Select("select a.*,b.name as publisher_name from essay a,user b where a.publisher=#{u_id} and a.publisher=b.u_id and a.type=#{type}")
	public List<Map<String,Object>> byId(@Param(value = "u_id") int u_id,@Param(value = "type") String type);
	
	@Select("select a.*,b.name as publisher_name from essay a,user b where e_id=#{e_id} and a.publisher=b.u_id")
	public List<Map<String,Object>> byEId(@Param(value = "e_id") int e_id);
	
	@Select("select * from essay where e_id=#{e_id} and publisher=#{publisher}")
	public List<Map<String,Object>> byEidAndPublisher(
			@Param(value = "e_id") int e_id,
			@Param(value = "publisher") int publisher);
	
	@Select("SELECT a.*,b.name as publisher_name from" + 
			" (select * from essay where title like CONCAT('%',#{key},'%') or content like CONCAT('%',#{key},'%')) a," + 
			" user b where a.publisher=b.u_id")
	public List<Map<String,Object>> search(@Param(value = "key") String key);
	
	@Insert("insert into essay(title,content,creation_time,update_time,publisher,label,type,info) "
			+ "VALUES(#{title},#{content},#{creation_time},#{update_time},#{publisher},#{label},#{type},#{info})")
	public int insert(
		@Param(value = "title") String title,
		@Param(value = "content") String content,
		@Param(value = "creation_time") String creation_time,
		@Param(value = "update_time") String update_time,
		@Param(value = "publisher") int publisher,
		@Param(value = "label") String label,
		@Param(value = "type") String type,
		@Param(value = "info") String info
	);
	
	@Delete("delete from essay where e_id=#{e_id};") 
	public int delete(@Param(value = "e_id") int e_id);
	
	/**更新文章 */
	@Update("update essay set title=#{title},content=#{content},update_time=#{time} where e_id=#{e_id}")
	public int update(
		@Param(value = "e_id") int e_id,
		@Param(value = "title") String title,
		@Param(value = "content") String content,
		@Param(value = "time") String time
	);

	/**更新其他 */
	@Update("update essay set label=#{label},info=#{info},privacy=#{privacy},support=#{support},"
		+"oppose=#{oppose},comment=#{comment},browse=#{browse} where e_id=#{e_id}")
	public int updateOthers(
		@Param(value = "e_id") int e_id,
		@Param(value = "label") int label,
		@Param(value = "info") String info,
		@Param(value = "privacy") String privacy,
		@Param(value = "support") int support,
		@Param(value = "oppose") int oppose,
		@Param(value = "comment") int comment,
		@Param(value = "browse") int browse
	);
	/**更新时间 */
	@Update("update essay set update_time=#{time} where e_id=#{e_id}")
	public int updateTime(@Param(value = "e_id") int e_id,@Param(value = "time") String time);
	
}
