/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 09, 2009
 */

package com.itgrids.partyanalyst.servlets;



import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4JServlet extends HttpServlet {


	  private static Logger logger = Logger.getLogger(Log4JServlet.class);
	
   public void init() throws ServletException{
	   logger.debug("Debug: Log4jServlet initialized");
	   logger.fatal("fatal: Log4jServlet initialized");
	   logger.error("error: Log4jServlet initialized");
      String log4jfile = getInitParameter("log4j-init-file");
      if (log4jfile != null) {
         String propfile = getServletContext().getRealPath(log4jfile);
         PropertyConfigurator.configure(propfile);
      }
   }
}

