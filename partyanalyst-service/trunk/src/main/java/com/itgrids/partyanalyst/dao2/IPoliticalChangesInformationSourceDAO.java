package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.hibernate.PoliticalChangesInformationSourceDAO;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.PoliticalChangesInformationSource;

public interface IPoliticalChangesInformationSourceDAO extends
		GenericDao<PoliticalChangesInformationSource, Long> {
	
	public List<PoliticalChangesInformationSource> findBySourceId(Long sourceId);

	public List<PoliticalChangesInformationSource> findBySourceName(String sourceName);

	public List<PoliticalChangesInformationSource> findBySescription(String description);

	public List<PoliticalChangesInformationSource> getAllProblemSources();
	
}
