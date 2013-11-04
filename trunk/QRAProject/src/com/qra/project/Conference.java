package com.qra.project;

import com.google.appengine.api.datastore.Key;

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
	private ArrayList<Date> dates;
	
	@Persistent
	private String conference_description;

	public Conference(String conf_name, String host_ID, ArrayList<Date> dates, String conference_description)
	{
		this.conf_name = conf_name;
		this.host_ID = host_ID;
		this.dates = dates;
		this.conference_description = conference_description;
		
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

	public ArrayList<Date> getDates() {
		return dates;
	}

	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
	}

	public String getConference_description() {
		return conference_description;
	}

	public void setConference_description(String conference_description) {
		this.conference_description = conference_description;
	}

	public Key getConf_code() {
		return conf_code;
	}

}
