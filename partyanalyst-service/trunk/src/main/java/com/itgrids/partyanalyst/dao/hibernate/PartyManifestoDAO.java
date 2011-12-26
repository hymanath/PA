package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyManifestoDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.PartyManifesto;

public class PartyManifestoDAO extends
		GenericDaoHibernate<PartyManifesto, Long> implements IPartyManifestoDAO {

	public PartyManifestoDAO() {
		super(PartyManifesto.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyManifestoInfo(Long partyId) {
		return getHibernateTemplate()
				.find(
						"select model.party.longName,model.party.shortName,model.election.electionScope.electionType.electionType,model.election.electionScope.state.stateName,"
								+ "model.election.electionYear,model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileDescription,model.file.language.language "
								+ "from PartyManifesto model where  model.party.partyId=?",
						partyId);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getSelectedState(Long partyId) {
			return getHibernateTemplate().find("select distinct model.state.stateId,model.state.stateName from PartyManifesto model where  model.party.partyId=?",partyId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getElectionTypes(Long partyId, Long stateId) {
		
		Object[] params = {partyId,stateId};
		return getHibernateTemplate().find("SELECT distinct model.election.electionScope.electionType.electionTypeId , model.election.electionScope.electionType.electionType from PartyManifesto model WHERE model.party.partyId =? and model.state.stateId =?",params );
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getElectionYearsBasedOnElectionTypeId(Long electionTypeId,Long partyId,Long stateId){
		
		Object[] params = {electionTypeId, partyId, stateId};
		return getHibernateTemplate().find("SELECT distinct model.election.electionYear , model.election.electionId FROM PartyManifesto model WHERE model.election.electionScope.electionType.electionTypeId =? and model.party.partyId =? and model.state.stateId =?",params);
	}

	public List<File> getPartyManifestoBasedOnElectionYear(Long electionId,
			Long partyId, Long stateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyManifestoInfo(Long partyId, String queryStr) {
		
		return getHibernateTemplate()
		.find(
				"select model.party.longName,model.party.shortName,model.election.electionScope.electionType.electionType,model.election.electionScope.state.stateName,"
						+ "model.election.electionYear,model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileDescription,model.file.language.language "
						+ "from PartyManifesto model where  model.party.partyId=? "+queryStr+"",
				partyId);
		
	}
	
}
