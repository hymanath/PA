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
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, model.constituency.localElectionBodyWard.wardName from AssemblyLocalElectionBodyWard model " +
				"where model.assemblyLocalElectionBody.localElectionBody.localElectionBodyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBodyWard model2 where model2.year <= ?)", params);
	}
	
	public List findByConstituencyIdAndYear(Long constituencyId, String wardType){
		Object[] params = {constituencyId, wardType};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, model.constituency.localElectionBody.name " +
				" from AssemblyLocalElectionBodyWard model where model.assemblyLocalElectionBody.constituency.constituencyId = ? and model.year = " +
				"(select max(model2.year) from AssemblyLocalElectionBodyWard model2) and model.constituency.localElectionBody.electionType.electionType = ?",params);
	}
	
	public List findByAssemblyLocalElectionBodyWardAndYear(Long assemblyLocalElectionBodyId, Long wardId, String year){
		Object[] params = {assemblyLocalElectionBodyId, wardId, year};
		return getHibernateTemplate().find("select model.constituency.constituencyId from AssemblyLocalElectionBodyWard model where " +
				"model.assemblyLocalElectionBody.assemblyLocalElectionBodyId = ? and model.constituency.constituencyId = ? and model.year = ?",params);
	}
	
	public List findByAssemblyLocalElectionBody(Long localElectionBodyId, String year) {
		Object[] params = {localElectionBodyId,year};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, model.constituency.localElectionBodyWard.wardName from AssemblyLocalElectionBodyWard model " +
				"where model.assemblyLocalElectionBody.assemblyLocalElectionBodyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBodyWard model2 where model2.year <= ?)", params);
	}	
	
	/*public List findByLocalElectionBody(Long localElectionBodyId, String year, Long constituencyId) {
		Object[] params = {localElectionBodyId,year, constituencyId};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, model.constituency.localElectionBodyWard.wardName from AssemblyLocalElectionBodyWard model " +
				"where model.assemblyLocalElectionBody.localElectionBody.localElectionBodyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBodyWard model2 where model2.year <= ?) and model.assemblyLocalElectionBody.constituency.constituencyId = ?", params);
	}*/

}
