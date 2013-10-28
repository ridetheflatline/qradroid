package com.qra.project;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class Session {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key sessionKey;

    @Persistent
    private String confCode;

    @Persistent
    private String description;
    
    @Persistent
    private Date startTime;
    
    @Persistent
    private Date endTime;

	public Session(Key sessionKey, String confCode, String description,
			Date startTime, Date endTime) {
		super();
		this.sessionKey = sessionKey;
		this.confCode = confCode;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Key getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(Key sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getConfCode() {
		return confCode;
	}

	public void setConfCode(String confCode) {
		this.confCode = confCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
    
}
