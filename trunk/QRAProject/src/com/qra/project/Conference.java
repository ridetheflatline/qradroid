package com.qra.project;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Conference {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key conf_code;
	
	@Persistent
	private String conf_name;
	
	@Persistent
	private String host_ID;
	
	@Persistent
	private Date startTime;
	
	@Persistent
	private Date endTime;
	
	@Persistent
	private String conference_description;

	public Conference(String conf_name, String host_ID, Date startTime,
			Date endTime, String conference_description) {
		super();
		this.conf_name = conf_name;
		this.host_ID = host_ID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.conference_description = conference_description;
	}

	public Conference(Key conf_code, String conf_name, String host_ID,
			Date startTime, Date endTime, String conference_description) {
		super();
		this.conf_code = conf_code;
		this.conf_name = conf_name;
		this.host_ID = host_ID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.conference_description = conference_description;
	}

	public String getConf_code() {
		return KeyFactory.keyToString(conf_code);
	}

	public String getConf_name() {
		return conf_name;
	}

	public void setConf_name(String conf_name) {
		this.conf_name = conf_name;
	}

	public String getHost_ID() {
		return host_ID;
	}

	public void setHost_ID(String host_ID) {
		this.host_ID = host_ID;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getConference_description() {
		return conference_description;
	}

	public void setConference_description(String conference_description) {
		this.conference_description = conference_description;
	}
}
