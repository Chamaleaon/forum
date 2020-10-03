package com.howay.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
  * 一级评论、层主
 * @author howay
 * @since 2020/9/3
 */
@Entity
@Table(name = "floor")
public class Floor {
	
	private int f_id;
	private int essay; //所属贴子id
	private String content;
	private int publisher; //发布者、层主。如果层主与楼主id相同，需要标注楼主
	private String creation_time;
	private String update_time;
	private int level; //具体几楼，比如1楼，按楼层排序
	private int support; //支持数、点赞数
	private int oppose;//反对数
	private String info;//自定义
	
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getEssay() {
		return essay;
	}
	public void setEssay(int essay) {
		this.essay = essay;
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

	public int getSupport() {
		return support;
	}

	public void setSupport(int support) {
		this.support = support;
	}

	public int getOppose() {
		return oppose;
	}

	public void setOppose(int oppose) {
		this.oppose = oppose;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	

}
