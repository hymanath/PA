package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyManifestoDAO;
import com.itgrids.partyanalyst.model.PartyManifesto;

public class PartyManifestoDAO extends
		GenericDaoHibernate<PartyManifesto, Long> implements IPartyManifestoDAO {

	public PartyManifestoDAO() {
		super(PartyManifesto.class);
	}

	public List<Object[]> getPartyManifestoInfo(Long partyId) {
		return getHibernateTemplate()
				.find(
						"select model.party.longName,model.party.shortName,model.election.electionScope.electionType.electionType,model.election.electionScope.state.stateName,"
								+ "model.election.electionYear,model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileDescription,model.file.language.language "
								+ "from PartyManifesto model where  model.party.partyId=?",
						partyId);
	}

	public List<Object[]> getSelectedState(Long partyId) {
return getHibernateTemplate().find("select model.state.stateId,model.state.stateName from PartyManifesto model where  model.party.partyId=?",partyId);
	}
}
