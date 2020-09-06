package com.howay.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 二级评论
* @author howay
* @since 2020/9/3
*/
@Entity
@Table(name = "layer")
public class Layer {
	
	private int l_id; //该二级评论id————(主键，唯一标识，所有表必不可少的)
	private int floor; //所属一级评论id
	private String content;
	private int publisher; //发布人id
	private int responder; //被回复的人id
	private String creation_time;
	private String update_time;
	private int level; //层数，第几层
	private int replied_lid; //回复的具体二级评论的id，如果为-1，则直接回复该楼层
	
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPublisher() {
		return publisher;
	}
	public void setPublisher(int publisher) {
		this.publisher = publisher;
	}
	public int getResponder() {
		return responder;
	}
	public void setResponder(int responder) {
		this.responder = responder;
	}
	public String getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getReplied_lid() {
		return replied_lid;
	}
	public void setReplied_lid(int replied_lid) {
		this.replied_lid = replied_lid;
	}
	

}
