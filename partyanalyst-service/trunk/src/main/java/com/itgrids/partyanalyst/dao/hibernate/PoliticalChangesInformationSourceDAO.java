package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IPoliticalChangesInformationSourceDAO;
import com.itgrids.partyanalyst.model.PoliticalChangesInformationSource;

public class PoliticalChangesInformationSourceDAO extends
		GenericDaoHibernate<PoliticalChangesInformationSource, Long> implements
		IPoliticalChangesInformationSourceDAO {

	public PoliticalChangesInformationSourceDAO() {
		super(PoliticalChangesInformationSource.class);
	}

	@SuppressWarnings("unchecked")
	public List<PoliticalChangesInformationSource> findBySourceId(Long sourceId) {
		return getHibernateTemplate().find("from PoliticalChangesInformationSource model where model.sourceId = ?", sourceId);
	}
	
	@SuppressWarnings("unchecked")
	public List<PoliticalChangesInformationSource> findBySourceName(
			String sourceName) {
		return getHibernateTemplate().find("from PoliticalChangesInformationSource model where model.sourceName = ?", sourceName);
	}
	
	@SuppressWarnings("unchecked")
	public List<PoliticalChangesInformationSource> findBySescription(
			String description) {
		return getHibernateTemplate().find("from PoliticalChangesInformationSource model where model.description = ?", description);
	}

	@SuppressWarnings("unchecked")
	public List<PoliticalChangesInformationSource> getAllProblemSources() {
		return getHibernateTemplate().find("select model.sourceId,model.sourceName from PoliticalChangesInformationSource model ");
	}
	

}
