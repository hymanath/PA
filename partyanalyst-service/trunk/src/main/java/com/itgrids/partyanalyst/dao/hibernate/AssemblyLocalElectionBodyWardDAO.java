package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBodyWard;

public class AssemblyLocalElectionBodyWardDAO  extends GenericDaoHibernate<AssemblyLocalElectionBodyWard, Long> implements IAssemblyLocalElectionBodyWardDAO  {

	public AssemblyLocalElectionBodyWardDAO() {
		super(AssemblyLocalElectionBodyWard.class);
	 
	}

	public List findByLocalElectionBody(Long localElectionBodyId, String year) {
		Object[] params = {localElectionBodyId,year};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name from AssemblyLocalElectionBodyWard model " +
				"where model.assemblyLocalElectionBody.localElectionBody.localElectionBodyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBodyWard model2 where model2.year <= ?)", params);
	}
	
	public List findByConstituencyIdAndYear(Long constituencyId, String wardType){
		Object[] params = {constituencyId, wardType};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, model.constituency.localElectionBody.name " +
				" from AssemblyLocalElectionBodyWard model where model.assemblyLocalElectionBody.constituency.constituencyId = ? and model.year = " +
				"(select max(model2.year) from AssemblyLocalElectionBodyWard model2) and model.constituency.localElectionBody.electionType.electionType = ?",params);
	}

}
