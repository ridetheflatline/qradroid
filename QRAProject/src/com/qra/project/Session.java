package com.qra.project;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Session implements Serializable{
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
    
    @Persistent
    private String timeZone;

	public Session(String description, Date startTime, Date endTime) {
		super();
		this.confCode = "";
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Session(String confCode, String description, Date startTime,
			Date endTime) {
		super();
		this.confCode = confCode;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Session(String confCode, String description, Date startTime,
			Date endTime, String timeZone) {
		super();
		this.confCode = confCode;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeZone = timeZone;
	}
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
	
	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getStartDateAsFormattedString(){
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy h:m a");
		TimeZone ts = TimeZone.getTimeZone(this.timeZone);
		formatter.setTimeZone(ts);
		return formatter.format(this.startTime);
	}
	public String getEndDateAsFormattedString(){
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy h:m a");
		TimeZone ts = TimeZone.getTimeZone(this.timeZone);
		formatter.setTimeZone(ts);
		return formatter.format(this.endTime);
	}
}
