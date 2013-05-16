package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.model.VoterCastInfo;

public class VoterCastInfoDAO extends GenericDaoHibernate<VoterCastInfo,Long> implements IVoterCastInfoDAO{

	public VoterCastInfoDAO()
	{
		super(VoterCastInfo.class);
	}
	
	
	public Integer deleteVotersCastInfoByReportLevelValue(Long reportLevelValue, Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery("delete from VoterCastInfo model where model.constituency.constituencyId=:reportLevelValue and model.publicationDateId = :publicationDateId and model.userId = :userId");
		
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		return query.executeUpdate();	
	}
	
    public List<VoterCastInfo>  getVotersCastInfo(Long levelId,Long levelValue,Long constituencyId,Long publicationId,Long userId){
		
		Query query = getSession().createQuery("from VoterCastInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
				"model.reportLevelValue = :levelValue and model.constituency.constituencyId = :constituencyId and model.publicationDateId = :publicationId " +
				" and model.userId = :userId ");
		
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public Long getRecordsCountToCheckDataPresent(Long constituencyId){
		Query query = getSession().createQuery("select count(*) from VoterCastInfo model where model.constituency.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return (Long)query.uniqueResult();
	}
	
    public List<VoterCastInfo> getVotersCastInfoByMultipleLevelValues(Long levelId,Set<Long> levelValues,Long constituencyId,Long publicationId,Long userId){
		
		Query query = getSession().createQuery("from VoterCastInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
				"model.reportLevelValue in (:levelValues) and model.constituency.constituencyId = :constituencyId and model.publicationDateId = :publicationId " +
				" and model.userId = :userId ");
		
		query.setParameter("levelId", levelId);
		query.setParameterList("levelValues", levelValues);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
    
    public void saveAllObjects(List<VoterCastInfo> voterCastInfos){
    	getHibernateTemplate().saveOrUpdateAll(voterCastInfos);
    }
    
     public Long  getVotersCastCount(Long levelId,Long levelValue,Long constituencyId,Long publicationId,Long userId){
		
		Query query = getSession().createQuery("select sum(model.casteVoters) from VoterCastInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
				"model.reportLevelValue = :levelValue and model.constituency.constituencyId = :constituencyId and model.publicationDateId = :publicationId " +
				" and model.userId = :userId ");
		
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		return (Long)query.uniqueResult();
	}
     
     public void flushAndclearSession(){
			getSession().flush();
			getSession().clear();
		}
    
     public List<VoterCastInfo> getVotersCastInfoByMultipleLevelValuesAndCastIds(Long levelId,Set<Long> levelValues,List<Long> castStateIds,Long constituencyId,Long publicationId,Long userId){
 		
 		Query query = getSession().createQuery("from VoterCastInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
 				"model.reportLevelValue in (:levelValues) and model.constituency.constituencyId = :constituencyId and model.publicationDateId = :publicationId " +
 				" and model.userId = :userId and model.casteState.casteStateId in(:castStateIds) ");
 		
 		query.setParameter("levelId", levelId);
 		query.setParameterList("levelValues", levelValues);
 		query.setParameter("constituencyId", constituencyId);
 		query.setParameter("publicationId", publicationId);
 		query.setParameter("userId", userId);
 		query.setParameterList("castStateIds", castStateIds);
 		
 		return query.list();
 	}
     
     public List<Object[]> getCastAndPartyForSelectedLevel(Long userId,Long reportId ,List<Long> ids,Long publicationId)
     {
    	 Query query = getSession().createQuery("select distinct(model.casteState.casteStateId) , model.casteState.caste.casteName " +
    	 		" from VoterCastInfo model where model.userId = :userId and model.voterReportLevel.voterReportLevelId = :reportId " +
    	 		" and model.reportLevelValue in (:ids) and model.publicationDateId = :publicationId order by model.casteState.caste.casteName " );
    	 query.setParameter("userId", userId);
    	 query.setParameter("reportId", reportId);
    	 query.setParameterList("ids", ids);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
}
