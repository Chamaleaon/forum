package com.howay.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 一级评论、层主
* @author howay
* @since 2020/9/6
*/
@Entity
@Table(name = "relation")
public class Relations {
	
	private int id;
	private int recipient_id; //收到人id
	private int sender_id; //发送人id
	private String r_type; //收到人消息类别 ["ESSAY","FLOOR","LAYER"]
	private String s_type; //发送人消息类别 ["FLOOR","LAYER"]
	private int rt_id; //收到人消息id
	private int st_id; //发送人消息id
	private String time; //改关系建立时间，由发送人发送时间为准
	private boolean check; //发送人是否查询该消息 true:已查看  false:未查看
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRecipient_id() {
		return recipient_id;
	}
	public void setRecipient_id(int recipient_id) {
		this.recipient_id = recipient_id;
	}
	public int getSender_id() {
		return sender_id;
	}
	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
	}
	public String getR_type() {
		return r_type;
	}
	public void setR_type(String r_type) {
		this.r_type = r_type;
	}
	public String getS_type() {
		return s_type;
	}
	public void setS_type(String s_type) {
		this.s_type = s_type;
	}
	public int getRt_id() {
		return rt_id;
	}
	public void setRt_id(int rt_id) {
		this.rt_id = rt_id;
	}
	public int getSt_id() {
		return st_id;
	}
	public void setSt_id(int st_id) {
		this.st_id = st_id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	

}
