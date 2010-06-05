package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPoliticalChangesDAO;
import com.itgrids.partyanalyst.model.PoliticalChanges;

public class PoliticalChangesDAO extends GenericDaoHibernate<PoliticalChanges, Long>
		implements IPoliticalChangesDAO {

	public PoliticalChangesDAO() {
		super(PoliticalChanges.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<PoliticalChanges> findByPoliticalChangesId(
			Long politicalChangesId) {
		return getHibernateTemplate().find("from PoliticalChanges model where model.politicalChangesId = ?", politicalChangesId);
	}
	
	@SuppressWarnings("unchecked")
	public List<PoliticalChanges> findByDescription(String description) {
		return getHibernateTemplate().find("from PoliticalChanges model where model.description = ?", description);
	}

	@SuppressWarnings("unchecked")
	public List<PoliticalChanges> findByIdentifiedDate(
			String identifiedDate) {
		return getHibernateTemplate().find("from PoliticalChanges model where model.identifiedDate = ?", identifiedDate);
	}

	@SuppressWarnings("unchecked")
	public List<PoliticalChanges> findByOccuredDate(String occuredDate) {
		return getHibernateTemplate().find("from PoliticalChanges model where model.occuredDate = ?", occuredDate);
	}

	@SuppressWarnings("unchecked")
	public List<PoliticalChanges> findBySourceOfInformation(
			String sourceOfInformation) {
		return getHibernateTemplate().find("from PoliticalChanges model where model.sourceOfInformation = ?", sourceOfInformation);
	}

	@SuppressWarnings("unchecked")
	public List<PoliticalChanges> findByUpdatedDate(String updatedDate) {
		return getHibernateTemplate().find("from PoliticalChanges model where model.updatedDate = ?", updatedDate);
	}


	@SuppressWarnings("unchecked")
	public List<PoliticalChanges> getPartyNameByPoliticalChangesId(
			Long politicalChangesId) {
		return getHibernateTemplate().find("select model.party.partyId from PoliticalChanges model where model.politicalChangesId = ?", politicalChangesId);
	}

	@SuppressWarnings("unchecked")
	public List<PoliticalChanges> getSourceNameByPoliticalChangesId(
			Long politicalChangesId) {
		return getHibernateTemplate().find("select model.politicalChangesInformationSource.sourceId from PoliticalChanges model where model.politicalChangesId = ?", politicalChangesId);
	}

	@SuppressWarnings("unchecked")
	public List getAllPoliticalChangesByUserId(
			Long userId) {
		return getHibernateTemplate().find("select model.title,model.description,model.occuredDate," +
				" model.registration.firstName,model.registration.lastName," +
				" model.registration.registrationId,model.politicalChangesInformationSource.sourceId," +
				" model.politicalChangesInformationSource.sourceName,model.politicalChangesId,model.identifiedDate,model.party.shortName from PoliticalChanges model " +
				" where model.registration.registrationId = ? and model.isDelete is null order by model.updatedDate", userId);
	}
		
}
