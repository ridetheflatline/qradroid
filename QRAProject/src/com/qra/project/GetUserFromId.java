package com.qra.project;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class GetUserFromId {
	
	public static User getUser(String sessId, String cookieId){
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(User.class);
		
		q.setFilter("username == usernameParam");
		q.declareParameters("String usernameParam");
		
		List<User> myUser = null;
		
		if(sessId != null && sessId != "")
			myUser = (List<User>)q.execute(sessId);
		else
			myUser = (List<User>)q.execute(cookieId);
		
		if(myUser.size() > 0)
			return myUser.get(0);
		else
			return null;
	}

}
