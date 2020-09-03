package com.howay.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
  * 贴子，一般为楼主所发的文章、问题等等
 * 
 * @author howay
 * @since 2020/9/3
 */
@Entity
@Table(name = "essay")
public class Essay {
	
	private int e_id;
	private String title; //标题
	private String content; //内容
	private String creation_time; //创建时间
	private String update_time; //更新时间
	private int publisher; // 发布者，为楼主id
	private String label; // 标签
	
	
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getPublisher() {
		return publisher;
	}
	public void setPublisher(int publisher) {
		this.publisher = publisher;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
