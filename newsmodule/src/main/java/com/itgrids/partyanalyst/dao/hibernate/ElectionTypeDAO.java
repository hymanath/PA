package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IElectionTypeDAO; 
import com.itgrids.partyanalyst.dao.columns.enums.ElectionColumnNames;
import com.itgrids.partyanalyst.dao.columns.enums.ElectionTypeColumnNames;
import com.itgrids.partyanalyst.model.ElectionType;
/**
 * @author <a href="mailto:mritlsk@yahoo.com">Inayatullah</a>
 *
 */
public class ElectionTypeDAO extends GenericDaoHibernate<ElectionType, Long> implements IElectionTypeDAO{

	public ElectionTypeDAO() {
		super(ElectionType.class);
	}
/*
	public List<ElectionType> findByElectionType(Object electionType) {
		
		return findByProperty(ElectionTypeColumnNames.ELECTION_TYPE, electionType);
	}

	public List<ElectionType> findByProperty(ElectionTypeColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from ElectionType where " + propertyName.getValue() + "=?", value);
	}

	public List<ElectionType> findByScope(Object scope) {
		
		return findByProperty(ElectionTypeColumnNames.SCOPE, scope);
	}*/
 
}
