package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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

	public List<ElectionType> findByElectionType(Object electionType) {
		
		return findByProperty(ElectionTypeColumnNames.ELECTION_TYPE, electionType);
	}

	public List<ElectionType> findByProperty(ElectionTypeColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from ElectionType where " + propertyName.getValue() + "=?", value);
	}

	public List<ElectionType> findByScope(Object scope) {
		
		return findByProperty(ElectionTypeColumnNames.SCOPE, scope);
	}
	
	public String getElectionTypeByTypeId(Long electionTypeId){
		Query queryObject = getSession().createQuery("select model.electionType from ElectionType model where model.electionTypeId " +
				"in( select model1.electionType.electionTypeId from ElectionScope model1 where model1.electionScopeId " +
				"in( select model2.electionScope.electionScopeId from Election model2 where model2.electionId=?))");	
		queryObject.setParameter(0,electionTypeId);	
		return (String) queryObject.uniqueResult();
	}
 
}
