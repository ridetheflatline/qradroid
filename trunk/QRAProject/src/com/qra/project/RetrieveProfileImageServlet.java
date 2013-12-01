package com.qra.project;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class RetrieveProfileImageServlet extends HttpServlet {
	
    private static final Logger log = Logger.getLogger(RetrieveProfileImageServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		 String blob_key_string = req.getParameter("profile_img_key");
		 log.info("BLOBKEY: " + blob_key_string);
		    if (blob_key_string == null) {
		        resp.sendRedirect("/index.html");
		        log.info("No blobkey given, redirecting to upload page.");
		    }
		    else {
		        BlobKey blob_key = new BlobKey(blob_key_string);
		        BlobstoreServiceFactory.getBlobstoreService().serve(blob_key, resp);
		        log.info("Blobkey given, sending file.");
		    }
	}
}
