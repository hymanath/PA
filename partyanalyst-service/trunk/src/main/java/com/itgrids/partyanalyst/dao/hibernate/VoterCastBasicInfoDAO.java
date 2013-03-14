package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.model.VoterCastBasicInfo;
import com.itgrids.partyanalyst.model.VoterCastInfo;

public class VoterCastBasicInfoDAO extends GenericDaoHibernate<VoterCastBasicInfo, Long> implements IVoterCastBasicInfoDAO{

	public VoterCastBasicInfoDAO() {
		super(VoterCastBasicInfo.class);
	}
	public Integer deleteVotersCastInfoByReportLevelValue(Long reportLevelValue, Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery("delete from VoterCastBasicInfo model where model.constituency.constituencyId=:reportLevelValue and model.publicationDateId = :publicationDateId and model.userId = :userId");
		
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		return query.executeUpdate();	
	}
	
    public List<VoterCastBasicInfo>  getVotersCastBasicInfo(Long levelId,Long levelValue,Long constituencyId,Long publicationId,Long userId){
		
		Query query = getSession().createQuery("from VoterCastBasicInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
				"model.reportLevelValue = :levelValue and model.constituency.constituencyId = :constituencyId and model.publicationDateId = :publicationId " +
				" and model.userId = :userId ");
		
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
    
    public void saveAllObjects(List<VoterCastBasicInfo> voterCastBasicInfos){
    	getHibernateTemplate().saveOrUpdateAll(voterCastBasicInfos);
    }
}
