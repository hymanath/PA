package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.model.Census;
import com.itgrids.partyanalyst.model.Election;

public class CensusDAO extends GenericDaoHibernate<Census, Long> implements ICensusDAO {

	public CensusDAO() {
		super(Census.class);
	}

	@SuppressWarnings("unchecked")
	public Set<Census> findByDistrictIdAndYear(final Long districtId, final int year) {
		return ( Set<Census> ) getHibernateTemplate().execute( new HibernateCallback() {

            public Object doInHibernate( Session session ) throws HibernateException, SQLException {

                Criteria criteria = session.createCriteria( Census.class )
                	.add( Expression.eq( "districtId" , districtId  ))
                	.add( Expression.eq( "year" , year  )); 
                
                return new HashSet( criteria.list() );
            }
        } );

	}

	@SuppressWarnings("unchecked")
	public Set<Census> findByStateIdAndYear(final Long stateId, final int year) {
		return ( Set<Census> ) getHibernateTemplate().execute( new HibernateCallback() {

            public Object doInHibernate( Session session ) throws HibernateException, SQLException {

                Criteria criteria = session.createCriteria( Census.class )
                	.add( Expression.eq( "stateId" , stateId  ))
                	.add( Expression.eq( "year" , year  )); 
                
                return new HashSet( criteria.list() );
            }
        } );

	}

	@SuppressWarnings("unchecked")
	public Set<Census> findByTehsilIdAndYear(final Long tehsilId, final int year) {
		return ( Set<Census> ) getHibernateTemplate().execute( new HibernateCallback() {

            public Object doInHibernate( Session session ) throws HibernateException, SQLException {

                Criteria criteria = session.createCriteria( Census.class )
                	.add( Expression.eq( "tehsilId" , tehsilId  ))
                	.add( Expression.eq( "year" , year  )); 
                
                return new HashSet( criteria.list() );
            }
        } );

	}

	@SuppressWarnings("unchecked")
	public Set<Census> findByTownshipIdAndYear(final Long townshipId, final int year) {
		return ( Set<Census> ) getHibernateTemplate().execute( new HibernateCallback() {

            public Object doInHibernate( Session session ) throws HibernateException, SQLException {

                Criteria criteria = session.createCriteria( Census.class )
                	.add( Expression.eq( "townshipId" , townshipId  ))
                	.add( Expression.eq( "year" , year  )); 
                
                return new HashSet( criteria.list() );
            }
        } );

	}

	@SuppressWarnings("unchecked")
	public Set<Census> findByWardIdAndYear( final Long wardId, final int year) {
		return ( Set<Census> ) getHibernateTemplate().execute( new HibernateCallback() {

            public Object doInHibernate( Session session ) throws HibernateException, SQLException {

                Criteria criteria = session.createCriteria( Census.class )
                	.add( Expression.eq( "wardId" , wardId  ))
                	.add( Expression.eq( "year" , year  )); 
                
                return new HashSet( criteria.list() );
            }
        } );

	}

	public List<Census> findByYearAndTehsilIDs(Long year, String tehsilIDs){
		return getHibernateTemplate().find("from Census model where model.year="+year+" and model.tehsilId in ("+tehsilIDs +")");
	}

}
