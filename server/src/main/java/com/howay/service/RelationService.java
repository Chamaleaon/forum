package com.howay.service;
/**
 * 
 * @author howay
 *
 */
public interface RelationService {
	//recipient_id,sender_id,r_type,s_type,rt_id,st_id,time
	public int insert(int recipient_id,int sender_id,String r_type,String s_type,int rt_id,int st_id,String time);
	public int delete(String s_type,int st_id);
	public String getMessages(int u_id);
	public String getReplies(int u_id);
}
