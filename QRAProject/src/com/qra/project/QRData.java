package com.qra.project;

public class QRData {

	private String confName;
	private String attName;
	private String userID;
	private String confID;
	private String dates;
	private String userName;
	
	/***
	 * Initialize an object that has all data needed for a QR ID to be printed.
	 * @param cName
	 * @param aName
	 * @param uID
	 * @param cID
	 * @param d
	 */
	public QRData(String cName, String aName, String uID, String cID, String d, String u){
		confName=cName;
		attName=aName;
		userID=uID;
		confID=cID;
		dates=d;
		userName=u;
	}
	
	/**
	 * @return the confName
	 */
	public String getConfName() {
		return confName;
	}
	/**
	 * @param confName the confName to set
	 */
	public void setConfName(String confName) {
		this.confName = confName;
	}
	/**
	 * @return the attName
	 */
	public String getAttName() {
		return attName;
	}
	/**
	 * @param attName the attName to set
	 */
	public void setAttName(String attName) {
		this.attName = attName;
	}
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return the confID
	 */
	public String getConfID() {
		return confID;
	}
	/**
	 * @param confID the confID to set
	 */
	public void setConfID(String confID) {
		this.confID = confID;
	}
	/**
	 * @return the dates
	 */
	public String getDates() {
		return dates;
	}
	/**
	 * @param dates the dates to set
	 */
	public void setDates(String dates) {
		this.dates = dates;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
