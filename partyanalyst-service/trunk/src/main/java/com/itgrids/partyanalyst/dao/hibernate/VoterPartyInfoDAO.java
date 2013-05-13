package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterPartyInfoDAO;
import com.itgrids.partyanalyst.model.VoterPartyInfo;

public class VoterPartyInfoDAO extends GenericDaoHibernate<VoterPartyInfo, Long>implements IVoterPartyInfoDAO{

	public VoterPartyInfoDAO() {
		super(VoterPartyInfo.class);
		
	}
	public Integer deleteVotersPartyInfoByConstituencyId(Long constituencyId, Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery("delete from VoterPartyInfo model where model.constituencyId=:constituencyId and model.publicationDateId = :publicationDateId and model.userId = :userId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		return query.executeUpdate();	
	}
	
    public List<VoterPartyInfo>  getVotersPartyInfo(Long levelId,Long levelValue,Long constituencyId,Long publicationId,Long userId){
		
		Query query = getSession().createQuery("from VoterPartyInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
				"model.reportLevelValue = :levelValue and model.constituencyId = :constituencyId and model.publicationDateId = :publicationId " +
				" and model.userId = :userId ");
		
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
    
    public Long getRecordsCountToCheckDataPresent(Long constituencyId){
		Query query = getSession().createQuery("select count(*) from VoterPartyInfo model where model.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return (Long)query.uniqueResult();
	}
	
    public void saveAllObjects(List<VoterPartyInfo> voterPartyInfos){
    	
    	getHibernateTemplate().saveOrUpdateAll(voterPartyInfos);
    }
    
public List<VoterPartyInfo>  getVotersPartyInfoByMultipleLevelValuesAndPartyIds(Long levelId,List<Long> levelValues,List<Long> partyIds,Long constituencyId,Long publicationId,Long userId){
		
		Query query = getSession().createQuery("from VoterPartyInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
				"model.reportLevelValue in(:levelValues) and model.constituencyId = :constituencyId and model.publicationDateId = :publicationId " +
				" and model.userId = :userId and model.party.partyId in(:partyIds)");
		
		query.setParameter("levelId", levelId);
		query.setParameterList("levelValues", levelValues);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		query.setParameterList("partyIds", partyIds);
		
		return query.list();
	}
}
