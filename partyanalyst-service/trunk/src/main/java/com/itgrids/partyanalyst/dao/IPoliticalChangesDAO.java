package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PoliticalChanges;

public interface IPoliticalChangesDAO extends GenericDao<PoliticalChanges, Long> {
	
	public List<PoliticalChanges> findByPoliticalChangesId(Long politicalChangesId);
	
	public List<PoliticalChanges> findByIdentifiedDate(String identifiedDate);
	
	public List<PoliticalChanges> findByOccuredDate(String occuredDate);
	
	public List<PoliticalChanges> findByUpdatedDate(String updatedDate);
	
	public List<PoliticalChanges> findByDescription(String description);
	
	public List<PoliticalChanges> findBySourceOfInformation(String sourceOfInformation);

	public List getAllPoliticalChangesByUserId(Long userId);
	
	public List getAllPoliticalChangesByPoliticalChangeId(Long politicalChangeId);
}
