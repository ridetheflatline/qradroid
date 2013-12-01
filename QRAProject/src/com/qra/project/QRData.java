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
	 * @return the attName
	 */
	public String getAttName() {
		return attName;
	}
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @return the confID
	 */
	public String getConfID() {
		return confID;
	}
	/**
	 * @return the dates
	 */
	public String getDates() {
		return dates;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

}
