package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.model.ElectionAlliance;

public class ElectionAllianceDAO extends GenericDaoHibernate<ElectionAlliance, Long> implements
IElectionAllianceDAO {

	public ElectionAllianceDAO() {
		super(ElectionAlliance.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ElectionAlliance> findByElectionYear(final String year) {
		return ( List<ElectionAlliance> ) getHibernateTemplate().execute( new HibernateCallback() {
            public Object doInHibernate( Session session ) throws HibernateException, SQLException {
            		List<ElectionAlliance> alliances = session.createCriteria(ElectionAlliance.class)
            							.createAlias("election", "elec")
            							.add(Expression.eq("elec.electionYear", year))
            							.list();
            		 return alliances;
            }
        });
	}

}
