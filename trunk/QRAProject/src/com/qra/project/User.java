package com.qra.project;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class User implements Serializable {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key ID;
	
	@Persistent
	private String first_name;
	
	@Persistent
	private String middle_name;
	
	@Persistent
	private String last_name;
	
	@Persistent
	private String email;
	
	@Persistent
	private String username;
	
	@Persistent
	private String password;
	
	@Persistent
	private String profile_img;
	
	@Persistent
	private String birthdate;

	/*This is another Branch*/
	public User(String first_name, String middle_name,
			String last_name, String email, String username, String password,
			String profile_img, String birthdate) {
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.profile_img = profile_img;
		this.birthdate = birthdate;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getMiddle_name() {
		return middle_name;
	}


	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getProfile_img() {
		return profile_img;
	}


	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}


	public String getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}


	public String getID() {
		return KeyFactory.keyToString(ID);
	}

}
