package com.hibernate.model;

// Generated 2015-3-20 21:36:04 by Hibernate Tools 3.2.2.GA

import java.util.Date;

/**
 * WeiboComment generated by hbm2java
 */
public class WeiboComment implements java.io.Serializable {

	private Integer id;
	private String cid;
	private String userId;
	private String content;
	private Date time;
	private String source;
	private String weiboid;

	public WeiboComment() {
	}

	public WeiboComment(String cid, String userId, String content, Date time,
			String source, String weiboid) {
		this.cid = cid;
		this.userId = userId;
		this.content = content;
		this.time = time;
		this.source = source;
		this.weiboid = weiboid;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getWeiboid() {
		return this.weiboid;
	}

	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}

}
