package com.itgrids.eliteclub.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;

public class OpenSessionInViewAutoFlushFilter extends OpenSessionInViewFilter {
	Logger log=LogManager.getLogger();
    /**
     * Overrides the getSession method and changes the default flush behaviour to AUTO.
     */
	@Override
	public  Session openSession(SessionFactory sessionFactory)
	        throws DataAccessResourceFailureException
	    {
	        try
	        {
	            Session session = super.openSession(sessionFactory);
	            session.setFlushMode(FlushMode.AUTO);
	            return session;
	        }
	        catch(HibernateException ex)
	        {
	            throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
	        }
	    }
 
	
   
}