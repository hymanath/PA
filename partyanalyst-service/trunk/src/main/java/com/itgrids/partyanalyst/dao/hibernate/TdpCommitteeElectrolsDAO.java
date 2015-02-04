package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolsDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrols;

public class TdpCommitteeElectrolsDAO extends GenericDaoHibernate<TdpCommitteeElectrols, Long>  implements ITdpCommitteeElectrolsDAO {
	public TdpCommitteeElectrolsDAO() {
		super(TdpCommitteeElectrols.class);
	}
	public List<Object[]> getTdpCommitteeElectrolsForTdpCadreIdList(List<Long> tdpCadreIdsList)
	{
		String queryStr = " select distinct model.tdpCadre.tdpCadreId ,model.tdpCommittee.tdpBasicCommittee.name," +
				" '', model.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommittee.tdpCommitteeLevelValue " +
				" from TdpCommitteeElectrols model where model.tdpCadre.tdpCadreId in (:tdpCadreIdsList)  and model.isDeleted = 'N'";
		Query query = getSession().createQuery(queryStr);
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	
	public Long checkUserAlreadyAddedToThisCommittee(Long tdpCadreId,Long levelId,Long levelValue,Long enrollId,Long commitTypeId,Long tdpCommitId){
		String queryStr = " select count(*) from TdpCommitteeElectrols model where model.tdpCadre.tdpCadreId =:tdpCadreId and model.tdpCommitteeLevel.tdpCommitteeLevelId=:levelId" +
				" and model.levelValue =:levelValue and model.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId =:enrollId and model.tdpCommitteeType.tdpCommitteeTypeId=:commitTypeId" +
				" and model.tdpCommittee.tdpCommitteeId =:tdpCommitId  and model.isDeleted = 'N' ";
		Query query = getSession().createQuery(queryStr);
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("enrollId", enrollId);
		query.setParameter("commitTypeId", commitTypeId);
		query.setParameter("tdpCommitId", tdpCommitId);
		return (Long)query.uniqueResult();
	}
	
	public Long checkUserAlreadyAddedToOtherAffiliatedCommittee(Long tdpCadreId,Long levelId,Long levelValue,Long enrollId){
		String queryStr = " select count(*) from TdpCommitteeElectrols model where model.tdpCadre.tdpCadreId =:tdpCadreId and model.tdpCommitteeLevel.tdpCommitteeLevelId=:levelId" +
				" and model.levelValue =:levelValue and model.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId =:enrollId and model.tdpCommitteeType.tdpCommitteeTypeId=2" +
				" and model.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId =2  and model.isDeleted = 'N' ";
		Query query = getSession().createQuery(queryStr);
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("enrollId", enrollId);;
		return (Long)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getElectrolsForPanchayatsWards(List<Long> locationIds, String locationType){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.tdpCadreId), ");
		if(locationType.equalsIgnoreCase("panchayat")){
			sb.append(" model.tdpCadre.userAddress.panchayat.panchayatId, ");
		}else{
			sb.append( " model.tdpCadre.userAddress.ward.constituencyId, ");
		}
		sb.append(" model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId from TdpCommitteeElectrols model where");
		
		
		if(locationType.equalsIgnoreCase("panchayat")){
			sb.append(" model.tdpCadre.userAddress.panchayat.panchayatId in(:locationIds) ");
		}else{
			sb.append( " model.tdpCadre.userAddress.ward.constituencyId in(:locationIds) ");
		}
		
		sb.append(" and model.isDeleted = 'N'");
		
		if(locationType.equalsIgnoreCase("panchayat")){
			sb.append(" group by model.tdpCadre.userAddress.panchayat.panchayatId, ");
		}else{
			sb.append( " group by model.tdpCadre.userAddress.ward.constituencyId, ");
		}
		
		sb.append(" model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("locationIds", locationIds);
		
		return query.list();
		
	}
}
