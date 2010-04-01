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
	public List<ElectionAlliance> findByElectionYearAndType(final String electionYear, final Long electionType) {
		Object[] params = {electionYear, electionType};
		return getHibernateTemplate().find("from ElectionAlliance as model where model.election.electionYear=? " +
				"and model.election.electionScope.electionType.electionTypeId=? ",params); 
	}
	
	@SuppressWarnings("unchecked")
	public List findGroupIdByElection(Long electionId){
		return getHibernateTemplate().find("select model.group.groupId,model.group.groupName from ElectionAlliance as model"+
				" where model.election.electionId = ?",electionId);
	}

}
