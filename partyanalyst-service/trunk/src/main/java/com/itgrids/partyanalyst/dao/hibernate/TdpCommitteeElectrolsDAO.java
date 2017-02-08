package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolsDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrols;

public class TdpCommitteeElectrolsDAO extends GenericDaoHibernate<TdpCommitteeElectrols, Long>  implements ITdpCommitteeElectrolsDAO {
	public TdpCommitteeElectrolsDAO() {
		super(TdpCommitteeElectrols.class);
	}
	public List<Object[]> getTdpCommitteeElectrolsForTdpCadreIdList(List<Long> tdpCadreIdsList , Long tdpCommitteeEnrollmentId)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.tdpCadre.tdpCadreId ,model.tdpCommittee.tdpBasicCommittee.name,'', model.tdpCommittee.tdpCommitteeLevelId,model.tdpCommittee.tdpCommitteeLevelValue "+
	    " from   TdpCommitteeElectrols model" + 
	    " where  model.tdpCadre.tdpCadreId in (:tdpCadreIdsList)  and model.isDeleted = 'N' ");
	    
		if(tdpCommitteeEnrollmentId != null && tdpCommitteeEnrollmentId.longValue() > 0l){
			sb.append(" and model.tdpCommitteeEnrollmentId = :tdpCommitteeEnrollmentId ");
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		if(tdpCommitteeEnrollmentId != null && tdpCommitteeEnrollmentId.longValue() > 0l){
			query.setParameter("tdpCommitteeEnrollmentId", tdpCommitteeEnrollmentId);
		}
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
	/*public List<Object[]> getElectrolsForPanchayatsWards(List<Long> locationIds, String locationType,List<Long> enrollIdsList,Date startDate,Date endDate){
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
		
		sb.append(" and model.isDeleted = 'N'" +
				" and model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId != 1 ");
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
			sb.append(" and model.tdpCommitteeEnrollmentId in (:enrollIdsList)");
		
		if(startDate != null && endDate != null)
			sb.append( " and ( (date(model.tdpCommittee.startedDate) between :startDate and :endDate )  OR  date(model.tdpCommittee.completedDate) between :startDate and :endDate )  )" );
			//sb.append(" and date(model.tdpCommittee.startedDate) between :startDate and :endDate");
			
		if(locationType.equalsIgnoreCase("panchayat")){
			sb.append(" group by model.tdpCadre.userAddress.panchayat.panchayatId, ");
		}else{
			sb.append( " group by model.tdpCadre.userAddress.ward.constituencyId, ");
		}
		
		sb.append(" model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId");
		
		Query query = getSession().createQuery(sb.toString());
			query.setParameterList("locationIds", locationIds);
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
			query.setParameterList("enrollIdsList", enrollIdsList);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
		
	}*/
	
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
		
		sb.append(" and model.isDeleted = 'N'" +
				" and model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId != 1 ");
		
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
	/*public List<Object[]> getElectrolsForPanchayatsWardsWithOutDuplicates(List<Long> locationIds, String locationType,List<Long> tdpCadreIds,List<Long> enrollIdsList,Date startDate,Date endDate){
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
		
		if(tdpCadreIds.size()>0){
			sb.append(" and model.tdpCadre.tdpCadreId not in(:tdpCadreIds)" );
		}
		
		sb.append(" and model.isDeleted = 'N'" +
				" and model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = 1 ");
		
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
			sb.append(" and model.tdpCommitteeEnrollmentId in (:enrollIdsList)");
		
		if(startDate != null && endDate != null)
			sb.append( " and ( (date(model.tdpCommittee.startedDate) between :startDate and :endDate )  OR  date(model.tdpCommittee.completedDate) between :startDate and :endDate )  )" );
			//sb.append(" and date(model.tdpCommittee.startedDate) between :startDate and :endDate ");
		
		if(locationType.equalsIgnoreCase("panchayat")){
			sb.append(" group by model.tdpCadre.userAddress.panchayat.panchayatId, ");
		}else{
			sb.append( " group by model.tdpCadre.userAddress.ward.constituencyId, ");
		}
		
		sb.append(" model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId");
		
		Query query = getSession().createQuery(sb.toString());
		if(tdpCadreIds.size()>0){
			query.setParameterList("tdpCadreIds", tdpCadreIds);
		}
		query.setParameterList("locationIds", locationIds);
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
			query.setParameterList("enrollIdsList", enrollIdsList);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return query.list();
		
	}*/
	public List<Object[]> getElectrolsForPanchayatsWardsWithOutDuplicates(List<Long> locationIds, String locationType,List<Long> tdpCadreIds){
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
		
		if(tdpCadreIds.size()>0){
			sb.append(" and model.tdpCadre.tdpCadreId not in(:tdpCadreIds)" );
		}
		
		sb.append(" and model.isDeleted = 'N'" +
				" and model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = 1 ");
		
		if(locationType.equalsIgnoreCase("panchayat")){
			sb.append(" group by model.tdpCadre.userAddress.panchayat.panchayatId, ");
		}else{
			sb.append( " group by model.tdpCadre.userAddress.ward.constituencyId, ");
		}
		
		sb.append(" model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId");
		
		Query query = getSession().createQuery(sb.toString());
		if(tdpCadreIds.size()>0){
			query.setParameterList("tdpCadreIds", tdpCadreIds);
		}
		query.setParameterList("locationIds", locationIds);
		return query.list();
		
	}
	
	
	public List<Object[]> getElctoralInfoForALocation(Long locationValue){
		
		
		char locId[]=locationValue.toString().toCharArray();
		Long type=Long.parseLong(Character.toString(locId[0]));
		StringBuffer id=new StringBuffer();
		for(int i=1;i<locId.length;i++){
			id.append(locId[i]);
		}
		Long locationId=Long.parseLong(id.toString());
		
		
		/*select distinct tdp_basic_committee.name, tdp_cadre.first_name,
		 tdp_cadre.image,tdp_cadre.membership_id 
		from tdp_committee_electrols,tdp_cadre,user_address,tdp_committee,tdp_basic_committee 
		where
		 tdp_committee_electrols.tdp_cadre_id=tdp_cadre.tdp_cadre_id and 
		tdp_cadre.address_id=user_address.user_address_id and
		 tdp_committee.tdp_basic_committee_id=tdp_basic_committee.tdp_basic_committee_id
		and tdp_committee_electrols.is_deleted='N' and 
		user_address.panchayat_id=1 order by tdp_basic_committee.tdp_basic_committee_id;*/
		
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.tdpCommittee.tdpBasicCommittee.name,model.tdpCadre.firstname, " +
				" model.tdpCadre.image,model.tdpCadre.memberShipNo, model.tdpCadre.tdpCadreId " +
				" from TdpCommitteeElectrols model " +
				" where  model.isDeleted='N'  ");
				if(type==1){
					str.append(" and model.tdpCadre.userAddress.panchayat.panchayatId =:locationId " );
				}
				else if(type==2){
					str.append(" and model.tdpCadre.userAddress.ward.constituencyId =:locationId " );
				}
				str.append("order by model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId");
				
				Query query = getSession().createQuery(str.toString());
				query.setParameter("locationId", locationId);
				
		return query.list();
	}
	 public List<Object[]> getElectrolsOfPanchayatAndWards(Long locationId, Long locationType, Long basicCommitteeTypeId){
			StringBuilder sb = new StringBuilder();
			sb.append(" select " +
					" model.tdpCadre.tdpCadreId," +
					" model.tdpCadre.firstname," +
					" model.tdpCadre.image," +
					" model.tdpCadre.memberShipNo" +
					" from TdpCommitteeElectrols model " +
					" where  ");
			if(locationType.equals(6l)){
				sb.append(" model.tdpCadre.userAddress.panchayat.panchayatId = :locationId ");
			}
			if(locationType.equals(8l)){
				sb.append(" model.tdpCadre.userAddress.ward.constituencyId = :locationId ");
			}
			
			sb.append(" and model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId= :basicCommitteeTypeId  ");
			
			
			Query query = getSession().createQuery(sb.toString());
			
			query.setParameter("locationId", locationId);
			query.setParameter("basicCommitteeTypeId", basicCommitteeTypeId);
			
			return query.list();
		}
	 
	 public List<Object[]> getElctoralInfoForALocation(Long locationValue,Long tdpCommitteeEnrollmentId){
			
			char locId[]=locationValue.toString().toCharArray();
			Long type=Long.parseLong(Character.toString(locId[0]));
			StringBuffer id=new StringBuffer();
			for(int i=1;i<locId.length;i++){
				id.append(locId[i]);
			}
			Long locationId=Long.parseLong(id.toString());
			
			
			StringBuilder str = new StringBuilder();
			str.append("select distinct model.tdpCommittee.tdpBasicCommittee.name,model.tdpCadre.firstname, " +
					" model.tdpCadre.image,model.tdpCadre.memberShipNo, model.tdpCadre.tdpCadreId " +
					" from TdpCommitteeElectrols model " +
					" where  model.isDeleted='N'  ");
					if(type==1){
						str.append(" and model.tdpCadre.userAddress.panchayat.panchayatId =:locationId " );
					}
					else if(type==2){
						str.append(" and model.tdpCadre.userAddress.ward.constituencyId =:locationId " );
					}
					str.append(" and model.tdpCommitteeEnrollmentId = :tdpCommitteeEnrollmentId ");
					str.append("order by model.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId");
					
					Query query = getSession().createQuery(str.toString());
					query.setParameter("locationId", locationId);
					query.setParameter("tdpCommitteeEnrollmentId", tdpCommitteeEnrollmentId);
			return query.list();
		}
}
