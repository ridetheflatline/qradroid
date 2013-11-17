package com.qra.project;

public class AttCalc {

	private String attName;

	private String[] presence;
	private int size;
	
	
	public AttCalc(String name, String[] pres, int sz){
		attName=name;
		presence=pres;
		size=sz;
	}
	
	public String getName(){
		return attName;
	}
	
	public int getSize(){
		return size;
	}
	
	public String[] getAtt(){
		return presence;
	}
}
