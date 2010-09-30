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
				"where model.assemblyLocaElectionBodyWard.localElectionBody.localElectionBodyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBodyWard model2 where model2.year <= ?)", params);
	}

}
