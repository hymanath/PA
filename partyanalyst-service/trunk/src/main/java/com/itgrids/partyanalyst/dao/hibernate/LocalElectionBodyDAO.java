package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.model.LocalElectionBody;

public class LocalElectionBodyDAO extends GenericDaoHibernate<LocalElectionBody, Long> 
			implements ILocalElectionBodyDAO{

	public LocalElectionBodyDAO() {
		super(LocalElectionBody.class);
	}

	@SuppressWarnings("unchecked")
	public List<LocalElectionBody> findByElectionTypeDistrictTehsilAndLEBName(
			Long electionTypeId, String districtName, String tehsilName,
			String localElecBodyName) {
		Object[] params = {electionTypeId,
				districtName, tehsilName, localElecBodyName};
		return getHibernateTemplate().find("from LocalElectionBody model where model.electionType.electionTypeId = ? and " +
				"model.tehsil.district.districtName = ? and model.tehsil.tehsilName = ? and model.name = ?", params);
	}

	public List findByElectionTypeAndState(Long electionTypeId, Long stateId) {
		Object[] params = {electionTypeId, stateId};
		return getHibernateTemplate().find("select model.localElectionBodyId, model.name from LocalElectionBody model where model.electionType.electionTypeId = ? and " +
				"model.district.state.stateId = ? order by model.name", params);
	}

}
