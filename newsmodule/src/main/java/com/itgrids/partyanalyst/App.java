package com.itgrids.partyanalyst;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.itgrids.partyanalyst.model.Blog;

public class App 
{
    public static void main( String[] args )
    {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        Transaction tx = session.beginTransaction();

        Blog b = new Blog();
        session.save(b);

        tx.commit();
    }
}
