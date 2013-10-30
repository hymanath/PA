package com.itgrids.partyanalyst.util;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

public class OpenSessionInViewAutoFlushFilter extends OpenSessionInViewFilter {

    /**
     * Overrides the getSession method and changes the default flush behaviour to AUTO.
     */
    @Override
    protected Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
        Session session = super.getSession( sessionFactory );
        session.setFlushMode( FlushMode.AUTO );
        return session;
    }

    @Override
    protected void closeSession( Session session , SessionFactory sessionFactory ) {
        session.flush();
        super.closeSession( session, sessionFactory );
       
    }
   
}