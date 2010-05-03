package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LocalElectionBody;

public interface ILocalElectionBodyDAO extends GenericDao<LocalElectionBody, Long>{

	List<LocalElectionBody> findByElectionTypeDistrictTehsilAndLEBName(Long electionTypeId, String districtName, String tehsilName,
			String localElecBodyName);

	
	
}
