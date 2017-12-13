package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingDocumentDAO;
import com.itgrids.partyanalyst.dto.PartyMeetingsInputVO;
import com.itgrids.partyanalyst.model.PartyMeetingDocument;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyMeetingDocumentDAO extends GenericDaoHibernate<PartyMeetingDocument,Long> implements IPartyMeetingDocumentDAO{

	public PartyMeetingDocumentDAO()
	{
		super(PartyMeetingDocument.class);
	}
	
	public List<Object[]> getPartyMeetingsDocumentsDetls(Long partyMeetingTypeId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMI.partyMeeting.partyMeetingId, count(distinct PMI.partyMeetingDocumentId)  from PartyMeetingDocument PMI   where " +
				"  PMI.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
		queryStr.append(" group by PMI.partyMeeting.partyMeetingId order by PMI.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
		return query.list();
	}
	
	public List<Object[]> getDocumentDetailsForMinutesAtr(Long partyMeetingId,String accessType,List<Long> accessValues){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.partyMeetingDocumentId,model.partyMeetingId,model.path,model.documentType,model.documentFormat," +
				"model.uploadedBy.userId,model.uploadedBy.firstName,model.updatedBy.userId,model.updatedBy.firstName,model.uploadedTime, model.documentName " +
				" from PartyMeetingDocument model " +
				" where model.partyMeetingId=:partyMeetingId and model.isDeleted='N' ");
		
		if(accessType !=null && accessType.equalsIgnoreCase("MP") && accessValues.size()>0){
			str.append(" and  model.partyMeeting.meetingAddress.constituency.constituencyId in (:accessValues) ");
		}else if(accessType !=null && accessType.equalsIgnoreCase("DISTRICT") && accessValues.size()>0){
			str.append(" and  model.partyMeeting.meetingAddress.district.districtId in (:accessValues) ");
		}
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("partyMeetingId", partyMeetingId);
		if(accessType !=null && ( accessType.equalsIgnoreCase("MP") || accessType.equalsIgnoreCase("DISTRICT") ) &&  accessValues.size()>0){
			query.setParameterList("accessValues", accessValues);
		}
		return query.list();
	}
	
	public Integer deletePartyMeetingDocument(Long docId){
		Query query = getSession().createQuery("update PartyMeetingDocument model set model.isDeleted = 'Y' where model.partyMeetingDocumentId = ?");
		
		query.setParameter(0, docId);
		
		return query.executeUpdate();
	}
	
	public List<Object[]> getPartyMeetingDocsOf(Long partyMeetingId, String documentSourceType,String accessType,List<Long> accessValues){// ATR/MINUTE
		
		StringBuilder str = new StringBuilder();
		str.append(" select model.partyMeetingDocumentId,model.partyMeetingId,model.path,model.documentType,model.documentFormat," +
				"model.uploadedBy.userId,model.uploadedBy.firstName,model.updatedBy.userId,model.updatedBy.firstName,model.uploadedTime, model.documentName " +
				" from PartyMeetingDocument model " +
				" where model.partyMeetingId=:partyMeetingId and model.isDeleted='N'" +
				" and model.documentType = :documentSourceType ");
		
		if(accessType !=null && accessType.equalsIgnoreCase("MP") && accessValues.size()>0){
			str.append(" and  model.partyMeeting.meetingAddress.constituency.constituencyId in (:accessValues) ");
		}else if(accessType !=null && accessType.equalsIgnoreCase("DISTRICT") && accessValues.size()>0){
			str.append(" and  model.partyMeeting.meetingAddress.district.districtId in (:accessValues) ");
		}
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("partyMeetingId", partyMeetingId);
		query.setParameter("documentSourceType", documentSourceType);
		
		if(accessType !=null && ( accessType.equalsIgnoreCase("MP") || accessType.equalsIgnoreCase("DISTRICT") ) &&  accessValues.size()>0){
			query.setParameterList("accessValues", accessValues);
		}
		
		return query.list();
	}
	
	/*
	 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
	 * @since 21-AUG-2015
	 * This DAO Call is to Get Total Documents Of Meeting
	 * @param List<Long> partyMeetingIds
	 * @return List<Object[]>  of PartyMeetingId, Count of Documents
	 */
	public List<Object[]> getPartyMeetingDocsOfMeetingIds(List<Long> partyMeetingIds){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId," +
				" model.documentType," +
				" count(model.partyMeetingDocumentId)" +
				" from PartyMeetingDocument model" +
				" where " +
				" model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" and model.isDeleted = 'N'" +
				" group by model.partyMeeting.partyMeetingId," +
				" model.documentType ");
		
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		return query.list();
	}
	
	public List<Object[]> getMinuteAtrDocumentSummaryForAMeeting(Long meetingId,String type,String accessType,List<Long> accessValues ){
		
		StringBuilder str = new StringBuilder();
		
		 str.append(" select model.path,model.documentName,model.partyMeeting.meetingName,date(model.partyMeeting.startDate) from PartyMeetingDocument model " +
				" where model.isDeleted='N' and model.partyMeeting.partyMeetingId=:meetingId and model.documentType=:type ");
		
		if(accessType !=null && accessType.equalsIgnoreCase("MP") && accessValues.size()>0){
			str.append(" and  model.partyMeeting.meetingAddress.constituency.constituencyId in (:accessValues) ");
		}else if(accessType !=null && accessType.equalsIgnoreCase("DISTRICT") && accessValues.size()>0){
			str.append(" and  model.partyMeeting.meetingAddress.district.districtId in (:accessValues) ");
		}
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("meetingId", meetingId);
		query.setParameter("type", type);
		
		if(accessType !=null && ( accessType.equalsIgnoreCase("MP") || accessType.equalsIgnoreCase("DISTRICT") ) &&  accessValues.size()>0){
			query.setParameterList("accessValues", accessValues);
		}
		
		return query.list();
		
	}
	
	public List<Long> getDocDetails(List<Long> meetingIds,String type){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId from PartyMeetingDocument model " +
				" where model.isDeleted='N' and model.partyMeeting.partyMeetingId in (:meetingIds) and model.documentType=:type ");
		query.setParameterList("meetingIds", meetingIds);
		query.setParameter("type", type);
		return query.list();
	}
	public List<Object[]> getPartyMeetingdocList(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
				" model.partyMeeting.partyMeetingId, " +
				" model.partyMeetingSession.partyMeetingSessionId," +
				" model.partyMeetingDocumentId , model.path,model.uploadedTime " +
				" from PartyMeetingDocument model ");
		
		if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0l){
			queryStr.append(",PartyMeetingGroupsMappingInfo model1 ");
		}
		
		queryStr.append(" where " +
				" model.isDeleted = 'N' " +
				" and model.partyMeeting.isActive = 'Y' " +
				" and model.partyMeetingSession.isDeleted = 'N' ");
		if(inputVO.getCategoryIdList() != null && inputVO.getCategoryIdList().size()>0){
			queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:partyMeetingLevelIds) " );
		}
		if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
			queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId " );
    	}  
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) " );
    	}
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0l){
			queryStr.append(" and model1.partyMeeting.partyMeetingId = model.partyMeeting.partyMeetingId " +
					" and model1.partyMeetingGroup.partyMeetingGroupId = :partyMeetngGrpId ");
		}
    	
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate " );
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 2L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 3L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 4L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 5L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationValuesSet) ");
    	}
    	queryStr.append(" order by model.uploadedTime desc ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
    		query.setParameter("partyMeetingMainTypeId", inputVO.getPartyMeetingMainTypeId());
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		query.setParameterList("partyMeetingTypeIdList", inputVO.getPartyMeetingTypeIds());
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		query.setDate("startDate",inputVO.getStartDate());
    		query.setDate("endDate",inputVO.getEndDate());
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		query.setParameter("stateId", inputVO.getStateId());
    	}
    	//List<Long> list = new ArrayList<Long>(0);
    	//list.addAll(2l,3l,4l,5l);
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && Arrays.asList(2L,3L,4L,5L).contains(locationId)){
    		query.setParameterList("locationValuesSet",locationValuesSet);  
    	}
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0l){
			 query.setParameter("partyMeetngGrpId", inputVO.getPartyMeetingGroupId()); 
		 }
    	if(inputVO.getCategoryIdList() != null && inputVO.getCategoryIdList().size()>0){
    		query.setParameterList("partyMeetingLevelIds",inputVO.getCategoryIdList()); 
    	}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]>  getDistrictsForCustomMeetingImgesLst(Long partyMeetingLevelId,Long stateId,Date fromDate,Date toDate,Long locationId,Set<Long> locationValuesSet,Long meetingId,Long meetingGrpId,Long locationValue){
		StringBuilder sb = new StringBuilder();
		
			if(meetingGrpId != null && meetingGrpId.longValue() > 0){
				sb.append("select model.userAddres.district.districtId," +
						" model.userAddres.district.districtName," +
						" count(model.partyMeetingDocumentId) " +
						" from PartyMeetingDocument model,PartyMeetingGroupsMappingInfo model2 " +
						" where model.isDeleted = 'N' " +
						" and model.partyMeeting.isActive = 'Y' and model.partyMeeting.partyMeetingId = model2.partyMeetingId  ");
				
				if(meetingGrpId != null && meetingGrpId.longValue() > 0){
					sb.append(" and model2.partyMeetingGroupsMappingInfoId = :meetingGrpId " );
				}
			}else{
				sb.append("select model.userAddres.district.districtId," +
						" model.userAddres.district.districtName," +
						" count(model.partyMeetingDocumentId) " +
						" from PartyMeetingDocument model " +
						" where model.isDeleted = 'N' " +
						" and model.partyMeeting.isActive = 'Y'  ");
			}
			if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l){
				sb.append(" and model.partyMeeting.partyMeetingLevelId = :partyMeetingLevelId");				
			}
			if(stateId != null && stateId.longValue() > 0L){
	    		sb.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
	    	}
			if(meetingId != null && meetingId.longValue() > 0){
				sb.append(" and model.partyMeeting.partyMeetingId = :meetingId " );
			}
			
			if(locationValue != null && locationValue.longValue() > 0l)
				sb.append(" and model.partyMeeting.meetingAddress.district.districtId = :locationValue");
			
			if(fromDate != null && toDate != null){
				sb.append(" and date(model.partyMeeting.startDate) between :fromDate and :toDate " );
			}
			if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 2L){
				sb.append(" and model.partyMeeting.meetingAddress.state.stateId in (:locationValuesSet) ");
	    	}
	    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 3L){
	    		sb.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationValuesSet) ");
	    	}
	    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 4L){
	    		sb.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationValuesSet) ");
	    	}
	    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 5L){
	    		sb.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationValuesSet) ");
	    	}
			sb.append(" group by model.userAddres.district.districtId order by model.userAddres.district.districtName asc");
		Query query =  getSession().createQuery(sb.toString());
		if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l)
		 query.setParameter("partyMeetingLevelId", partyMeetingLevelId);
		if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 )
    		query.setParameterList("locationValuesSet",locationValuesSet);  
		if(stateId != null && stateId.longValue() > 0L)
			 query.setParameter("stateId", stateId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate",fromDate);
			query.setDate("toDate",toDate);
		}
		if(meetingId != null && meetingId.longValue() > 0){
			query.setParameter("meetingId", meetingId);
		}
		if(meetingGrpId != null && meetingGrpId.longValue() > 0){
			query.setParameter("meetingGrpId", meetingGrpId);
		}
		if(locationValue != null && locationValue.longValue() > 0l)
			query.setParameter("locationValue", locationValue);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]>  getConstByDistrictIdForWiseCustomPartyMeetings(Long partyMeetingLevelId,Long districtId){
		StringBuilder sb = new StringBuilder();
			sb.append("select model.userAddres.constituency.constituencyId,model.userAddres.constituency.name," +
					" count(model.partyMeetingDocumentId) " +
					" from PartyMeetingDocument model " +
					" where model.isDeleted ='N' ");
			if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l)
				sb.append(" and model.partyMeeting.partyMeetingLevelId = :partyMeetingLevelId");
			if(districtId != null && districtId.longValue() > 0l)
				sb.append(" and model.userAddres.district.districtId = :districtId");
			
			sb.append(" group by model.userAddres.constituency.constituencyId order by model.userAddres.constituency.name asc");
		Query query =  getSession().createQuery(sb.toString());
		if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l)
			query.setParameter("partyMeetingLevelId", partyMeetingLevelId);
		if(districtId != null && districtId.longValue() > 0l)
			 query.setParameter("districtId", districtId);
		
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]>  getMandalsByconstituencyIdForWiseCustomPartyMeetings(Long partyMeetingLevelId,Long constitencyId){
		StringBuilder sb = new StringBuilder();
			sb.append("select model.userAddres.tehsil.tehsilId,model.userAddres.tehsil.tehsilName," +
					" count(model.partyMeetingDocumentId) " +
					" from PartyMeetingDocument model " +
					" where model.isDeleted ='N' ");
			if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l)
				sb.append(" and model.partyMeeting.partyMeetingLevelId = :partyMeetingLevelId");
			if(constitencyId != null && constitencyId.longValue() > 0l)
				sb.append(" and model.userAddres.constituency.constituencyId = :constitencyId");
			
			sb.append(" group by model.userAddres.tehsil.tehsilId order by model.userAddres.tehsil.tehsilName asc ");
		Query query =  getSession().createQuery(sb.toString());
		if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l)
		 query.setParameter("partyMeetingLevelId", partyMeetingLevelId);
		if(constitencyId != null && constitencyId.longValue() > 0l)
			 query.setParameter("constitencyId", constitencyId);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]>  getMuncipalityNamesByConstiencyIdForWiseCustomPartyMeetings(Long partyMeetingLevelId,Long constitencyId){
		StringBuilder sb = new StringBuilder();
			sb.append("select model.userAddres.localElectionBody.localElectionBodyId,model.userAddres.localElectionBody.name," +
					" count(model.partyMeetingDocumentId)," +
					" ET.electionType " +
					" from PartyMeetingDocument model,ElectionType ET" +
					" where model.isDeleted ='N' " +
					" and model.userAddres.localElectionBody.electionType.electionTypeId = ET.electionTypeId ");
			if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l)
				sb.append(" and model.partyMeeting.partyMeetingLevelId = :partyMeetingLevelId");
			if(constitencyId != null && constitencyId.longValue() > 0l)
				sb.append(" and model.userAddres.constituency.constituencyId = :constitencyId");
			
			sb.append(" group by model.userAddres.localElectionBody.localElectionBodyId order by model.userAddres.localElectionBody.name asc ");
		Query query =  getSession().createQuery(sb.toString());
		if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l)
		 query.setParameter("partyMeetingLevelId", partyMeetingLevelId);
		if(constitencyId != null && constitencyId.longValue() > 0l)
			 query.setParameter("constitencyId", constitencyId);
		
		return query.list();
	}
	public List<Object[]>  getPanchaytNamesByMandalIdForWiseCustomPartyMeetings(Long partyMeetingLevelId,Long mandalId){
		StringBuilder sb = new StringBuilder();
			sb.append("select model.userAddres.panchayat.panchayatId,model.userAddres.panchayat.panchayatName," +
					" count(model.partyMeetingDocumentId ) " +
					" from PartyMeetingDocument model " +
					" where model.isDeleted ='N' ");
			if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l)
				sb.append(" and model.partyMeeting.partyMeetingLevelId = :partyMeetingLevelId");
			if(mandalId != null && mandalId.longValue() > 0l)
				sb.append(" and model.userAddres.tehsil.tehsilId = :mandalId");
			sb.append(" group by model.userAddres.panchayat.panchayatId order by model.userAddres.panchayat.panchayatName asc ");
		Query query =  getSession().createQuery(sb.toString());
		if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l)
		 query.setParameter("partyMeetingLevelId", partyMeetingLevelId);
		if(mandalId != null && mandalId.longValue() > 0l)
			 query.setParameter("mandalId", mandalId);
		
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]>  getWardNamesByMuncipalityIdForWiseCustomPartyMeetings(Long partyMeetingLevelId,Long muncipalityId){
		StringBuilder sb = new StringBuilder();
			sb.append("select model.userAddres.ward.constituencyId,model.userAddres.ward.name," +
					" count(model.partyMeetingDocumentId) " +
					" from PartyMeetingDocument model,Constituency C" +
					" where model.isDeleted ='N' " +
					" and model.userAddres.ward.constituencyId = C.constituencyId ");
			if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l)
				sb.append(" and model.partyMeeting.partyMeetingLevelId = :partyMeetingLevelId");
			if(muncipalityId != null && muncipalityId.longValue() > 0l)
				sb.append(" and model.userAddres.localElectionBody.localElectionBodyId = :muncipalityId");
			sb.append(" group by model.userAddres.ward.constituencyId order by model.userAddres.ward.name asc ");
		Query query =  getSession().createQuery(sb.toString());
		if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l)
		 query.setParameter("partyMeetingLevelId", partyMeetingLevelId);
		if(muncipalityId != null && muncipalityId.longValue() > 0l)
			 query.setParameter("muncipalityId", muncipalityId);
		
		return query.list();
	}
	
	/**
	 * @ Teja
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getCustomWisePartyMeetingDocuments(Date startDate,Date endDate,Long locationLevelId,Set<Long> locationLevelvalues,int startIndex,int maxIndex,Long stateId,Long partyMeetingLevelId,Long meetingId,Long meetingGrpId,Long locationValue)
	{
		StringBuilder str = new StringBuilder();
		
		if(meetingGrpId != null && meetingGrpId.longValue() > 0){
		str.append("select model.documentName,model.path " +
				"  from PartyMeetingDocument model,PartyMeetingGroupsMappingInfo model2  " +
				"  where model.partyMeeting.partyMeetingLevelId = :partyMeetingLevelId" +
				"  and model.isDeleted = 'N' and model.partyMeeting.partyMeetingId = model2.partyMeetingId  " );
			
			str.append(" and model2.partyMeetingGroupsMappingInfoId = :meetingGrpId " );
		}else{
			str.append("select model.documentName,model.path " +
					"  from PartyMeetingDocument model where model.isDeleted = 'N' and model.partyMeeting.isActive = 'Y' " );
			if(partyMeetingLevelId  != null && partyMeetingLevelId.longValue()>0L)
				str.append("  and model.partyMeeting.partyMeetingLevelId = :partyMeetingLevelId");
		}
		
		if(stateId != null && stateId.longValue() > 0L){
			str.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
    	}
		if(meetingId != null && meetingId.longValue() > 0){
			str.append(" and model.partyMeeting.partyMeetingId = :meetingId " );
		}
		if(locationValue != null && locationValue.longValue() > 0l)
			str.append(" and model.partyMeeting.meetingAddress.district.districtId = :locationValue");
		
			if(locationLevelId != null && locationLevelId.longValue() > 0l){
				if(locationLevelvalues != null && locationLevelvalues.size() > 0){
					if(locationLevelId != null && locationLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
						 str.append("  and model.partyMeeting.meetingAddress.state.stateId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
							 str.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
							 str.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
							 str.append("  and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
							 str.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
							 str.append(" and model.partyMeeting.meetingAddress.constituency.localElectionBody.localElectionBodyId in (:locationLevelvalues) "); 
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
							 str.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:locationLevelvalues) "); 
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
							 str.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:locationLevelvalues) "); 
						 }
				}
			}
			
		if(startDate != null && endDate!= null)
		{
			//str.append(" and (( date(model.partyMeeting.startDate) between :startDate and :endDate ) or (date(model.partyMeeting.endDate) between :startDate and :endDate) ) ");
			str.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate " );
		}
		Query query = getSession().createQuery(str.toString());

		if(partyMeetingLevelId  != null && partyMeetingLevelId.longValue()>0L)
			query.setParameter("partyMeetingLevelId", partyMeetingLevelId);
		if(locationLevelId != null && locationLevelId.longValue() > 0l){
			if(locationLevelvalues != null && locationLevelvalues.size() > 0){
				query.setParameterList("locationLevelvalues", locationLevelvalues);
			}
		}
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		if(stateId != null && stateId.longValue() > 0L){
			query.setParameter("stateId", stateId);
    	}
		if(maxIndex > 0)
		{
			query.setFirstResult(startIndex);
			query.setMaxResults(maxIndex);
		}
		if(meetingId != null && meetingId.longValue() > 0){
			query.setParameter("meetingId", meetingId);
		}
		if(meetingGrpId != null && meetingGrpId.longValue() > 0){
			query.setParameter("meetingGrpId", meetingGrpId);
		}
		if(locationValue != null && locationValue.longValue() > 0l)
			query.setParameter("locationValue", locationValue);
		return query.list();
	}
	
	/**
	 * @ Teja
	 */
	
	@SuppressWarnings("unchecked")
	public Long getCustomWisePartyMeetingDocumentsCount(Date startDate,Date endDate,Long locationLevelId,Set<Long> locationLevelvalues,int startIndex,int maxIndex,Long stateId,Long partyMeetingLevelId,Long meetingId,Long meetingGrpId,Long locationValue)
	{
		StringBuilder str = new StringBuilder();
		if(meetingGrpId != null && meetingGrpId.longValue()>0L){
			str.append("select distinct count(model.partyMeetingDocumentId) " +
					"  from PartyMeetingDocument model,PartyMeetingGroupsMappingInfo model2  " +
					" where model.partyMeeting.partyMeetingLevelId = :partyMeetingLevelId" +
					"  and model.isDeleted = 'N' and model.partyMeeting.partyMeetingId = model2.partyMeetingId  " );
				str.append(" and model2.partyMeetingGroupsMappingInfoId = :meetingGrpId " );
		}else{
			str.append("select distinct count(model.partyMeetingDocumentId) " +
					"  from PartyMeetingDocument model  " +
					" where model.partyMeeting.partyMeetingLevelId = :partyMeetingLevelId" +
					"  and model.isDeleted = 'N' and model.partyMeeting.isActive = 'Y' " );
		}
			if(stateId != null && stateId.longValue() > 0L){
				str.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
	    	}
			if(meetingId != null && meetingId.longValue() > 0){
				str.append(" and model.partyMeeting.partyMeetingId = :meetingId " );
			}
			if(locationValue != null && locationValue.longValue() > 0l)
				str.append(" and model.partyMeeting.meetingAddress.district.districtId = :locationValue");
			
			if(locationLevelId != null && locationLevelId.longValue() > 0l){
				if(locationLevelvalues != null && locationLevelvalues.size() > 0){
					if(locationLevelId != null && locationLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
						 str.append("  and model.partyMeeting.meetingAddress.state.stateId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
							 str.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
							 str.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
							 str.append("  and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
							 str.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
							 str.append(" and model.partyMeeting.meetingAddress.constituency.localElectionBody.localElectionBodyId in (:locationLevelvalues) "); 
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
							 str.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:locationLevelvalues) "); 
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
							 str.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:locationLevelvalues) "); 
						 }
				}
			}
		if(startDate != null && endDate!= null)
		{
			//str.append(" and (( date(model.partyMeeting.startDate) between :startDate and :endDate ) or (date(model.partyMeeting.endDate) between :startDate and :endDate) ) ");
			str.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate " );
		}
		Query query = getSession().createQuery(str.toString());

		query.setParameter("partyMeetingLevelId", partyMeetingLevelId);
		if(locationLevelId != null && locationLevelId.longValue() > 0l){
			if(locationLevelvalues != null && locationLevelvalues.size() > 0){
				query.setParameterList("locationLevelvalues", locationLevelvalues);
			}
		}
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		if(stateId != null && stateId.longValue() > 0L){
			query.setParameter("stateId", stateId);
    	}
		if(maxIndex > 0)
		{
			//query.setFirstResult(startIndex);
			//query.setMaxResults(maxIndex);
		}
		if(meetingId != null && meetingId.longValue() > 0){
			query.setParameter("meetingId", meetingId);
		}
		if(meetingGrpId != null && meetingGrpId.longValue() > 0){
			query.setParameter("meetingGrpId", meetingGrpId);
		}
		if(locationValue != null && locationValue.longValue() > 0l)
			query.setParameter("locationValue", locationValue);
		return (Long) query.uniqueResult();
	}
	
	
	/**
	 * @author Srishailam Pittala
	 * @Date 11th March, 2017
	 * @Description to get session wise images count details in multi location meetings 
	 */
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSessionWisePartyMeetingDocumentsCount(Date startDate,Date endDate,Long locationLevelId,Set<Long> locationLevelvalues,int startIndex,int maxIndex,Long stateId,Long partyMeetingLevelId,Long meetingId,Long meetingGrpId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.partyMeetingSession.sessionTypeId, count(model.partyMeetingDocumentId) ");
		if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l){
			if(partyMeetingLevelId != null && partyMeetingLevelId.longValue()==1L){
				 str.append("  , count(distinct model.partyMeeting.meetingAddress.state.stateId) ");  
			 }else if(partyMeetingLevelId != null && partyMeetingLevelId.longValue()==2L){
				 str.append("   , count(distinct model.partyMeeting.meetingAddress.district.districtId  ) ");  
			 }else if(partyMeetingLevelId != null && partyMeetingLevelId.longValue()==3L){
				 str.append("    , count(distinct  model.partyMeeting.meetingAddress.constituency.constituencyId ) ");  
			 }else if(partyMeetingLevelId != null && partyMeetingLevelId.longValue()==4L){
				 str.append("   , count(distinct  model.partyMeeting.meetingAddress.tehsil.tehsilId  ) ");  
			 }else if(partyMeetingLevelId != null && partyMeetingLevelId.longValue()==5L){ //  town/division
				 str.append("   , count(distinct  model.partyMeeting.meetingAddress.constituency.localElectionBody.localElectionBodyId  ) "); 
			 }else if(partyMeetingLevelId != null && ( partyMeetingLevelId.longValue()==6L || partyMeetingLevelId.longValue()==8L) ){ 
				 str.append("   , count(distinct  model.partyMeeting.meetingAddress.ward.constituencyId ) "); 
			 }else if(partyMeetingLevelId != null && partyMeetingLevelId.longValue()==7L){ 
				 str.append("   , count(distinct  model.partyMeeting.meetingAddress.panchayat.panchayatId ) "); 
			 }else if(partyMeetingLevelId != null && partyMeetingLevelId.longValue()==9l){
				 str.append("   , count(distinct model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId  ) ");  
			 }
		}
		str.append(" , model.partyMeeting.partyMeetingType.partyMeetingTypeId, model.partyMeeting.partyMeetingId from PartyMeetingDocument model,PartyMeetingGroupsMappingInfo model2  " +
				" where model.partyMeeting.partyMeetingLevelId = :partyMeetingLevelId" +
				"  and model.isDeleted = 'N' and model.partyMeeting.isActive='Y' and model.partyMeeting.partyMeetingId = model2.partyMeetingId  " );
			if(stateId != null && stateId.longValue() > 0L){
				str.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
	    	}
			if(meetingId != null && meetingId.longValue() > 0){
				str.append(" and model.partyMeeting.partyMeetingId = :meetingId " );
			}
			if(meetingGrpId != null && meetingGrpId.longValue() > 0){
				str.append(" and model2.partyMeetingGroupsMappingInfoId = :meetingGrpId " );
			}
			if(locationLevelId != null && locationLevelId.longValue() > 0l){
				if(locationLevelvalues != null && locationLevelvalues.size() > 0){
					if(locationLevelId != null && locationLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
						 str.append("  and model.partyMeeting.meetingAddress.state.stateId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
							 str.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
							 str.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
							 str.append("  and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
							 str.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:locationLevelvalues) ");  
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
							 str.append(" and model.partyMeeting.meetingAddress.constituency.localElectionBody.localElectionBodyId in (:locationLevelvalues) "); 
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
							 str.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:locationLevelvalues) "); 
						 }else if(locationLevelId != null && locationLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
							 str.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:locationLevelvalues) "); 
						 }
				}
			}
			if(startDate != null && endDate!= null)
			{
				str.append(" and (( date(model.partyMeeting.startDate) between :startDate and :endDate ) or (date(model.partyMeeting.endDate) between :startDate and :endDate) ) ");
			}
			
		str.append(" group by model.partyMeetingSession.sessionTypeId ");
		Query query = getSession().createQuery(str.toString());

		query.setParameter("partyMeetingLevelId", partyMeetingLevelId);
		if(locationLevelId != null && locationLevelId.longValue() > 0l){
			if(locationLevelvalues != null && locationLevelvalues.size() > 0){
				query.setParameterList("locationLevelvalues", locationLevelvalues);
			}
		}
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		if(stateId != null && stateId.longValue() > 0L){
			query.setParameter("stateId", stateId);
    	}
		if(maxIndex > 0)
		{
			//query.setFirstResult(startIndex);
			//query.setMaxResults(maxIndex);
		}
		if(meetingId != null && meetingId.longValue() > 0){
			query.setParameter("meetingId", meetingId);
		}
		if(meetingGrpId != null && meetingGrpId.longValue() > 0){
			query.setParameter("meetingGrpId", meetingGrpId);
		}
		return query.list();
	}
	
	public List<Object[]> getImagesCountsForPartyMeetings(List<Long> partyMeetingIds){
		Query query = getSession().createQuery("select model.partyMeeting.partyMeetingId,count(model.partyMeetingDocumentId)" +
												" from PartyMeetingDocument model" +
												" where model.partyMeeting.partyMeetingId in (:partyMeetingIds)" +
												" and model.isDeleted = 'N' and model.partyMeeting.isActive='Y'" +
												" group by model.partyMeeting.partyMeetingId");
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		return query.list();
	}
	
	public List<Object[]> getMinuteAtrDocumentSummaryForMeetingsList(List<Long> partyMeetingIds,String type,String accessType,List<Long> accessValues){

		
		StringBuilder str = new StringBuilder();
		
		 str.append(" select distinct model.partyMeeting.partyMeetingId,model.path,model.documentName,model.partyMeeting.meetingName,date(model.partyMeeting.startDate) from PartyMeetingDocument model " +
				" where model.isDeleted='N' and model.partyMeeting.partyMeetingId in (:partyMeetingIds)");
		
		if(accessType !=null && accessType.equalsIgnoreCase("MP") && accessValues.size()>0){
			str.append(" and  model.partyMeeting.meetingAddress.constituency.constituencyId in (:accessValues) ");
		}else if(accessType !=null && accessType.equalsIgnoreCase("DISTRICT") && accessValues.size()>0){
			str.append(" and  model.partyMeeting.meetingAddress.district.districtId in (:accessValues) ");
		}
		if(type != null && type.equalsIgnoreCase("momText") || type != null && type.equalsIgnoreCase("ATR") || type != null && type.equalsIgnoreCase("MINUTE") || type != null && type.equalsIgnoreCase("atrText") ){
			str.append(" and model.documentType=:type ");
		}
		Query query = getSession().createQuery(str.toString());
		
		   query.setParameterList("partyMeetingIds", partyMeetingIds);
		if(type != null && type.equalsIgnoreCase("momText") || type != null && type.equalsIgnoreCase("ATR") || type != null && type.equalsIgnoreCase("MINUTE") || type != null && type.equalsIgnoreCase("atrText") ){
		   query.setParameter("type", type);
		}
		
		if(accessType !=null && ( accessType.equalsIgnoreCase("MP") || accessType.equalsIgnoreCase("DISTRICT") ) &&  accessValues.size()>0){
			query.setParameterList("accessValues", accessValues);
		}
		
		return query.list();
		
	
	}
public List<Object[]> getPartyMeetingFilesId(List<Long> partMeetingIds,String type){
		StringBuilder str = new StringBuilder();
		 str.append(" select distinct model.partyMeeting.partyMeetingId,model.path from PartyMeetingDocument model " +
				" where model.isDeleted='N' " );
		 if(partMeetingIds != null && partMeetingIds.size()>0){
			 str.append(" and model.partyMeeting.partyMeetingId in(:partMeetingIds) ");
		 }
		 if(type != null && type.equalsIgnoreCase("momText") || type != null && type.equalsIgnoreCase("ATR") || type != null && type.equalsIgnoreCase("MINUTE") || type != null && type.equalsIgnoreCase("atrText") ){
				str.append(" and model.documentType=:type ");
			}
		Query query = getSession().createQuery(str.toString());
		if(partMeetingIds != null && partMeetingIds.size()>0){
			query.setParameterList("partMeetingIds", partMeetingIds);
		 }
		if(type != null && type.equalsIgnoreCase("momText") || type != null && type.equalsIgnoreCase("ATR") || type != null && type.equalsIgnoreCase("MINUTE") || type != null && type.equalsIgnoreCase("atrText") ){
			query.setParameter("type", type);
		}
		return query.list();	
	}

public List<Object[]> getLocationWiseStateMeetingImages(List<Long> locationValues,Long locationTypeId,Date fromDate,Date toDate,Long partyMeetingMainTypeid,Long partyMeetingTypeId){
    
    //0-meetingStatus,1-levelId,2-level,3-count
    StringBuilder sb = new StringBuilder();
    
    sb.append(" select model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId,model.partyMeeting.partyMeetingType.partyMeetingMainType.meetingType," +
       "model.partyMeeting.partyMeetingType.partyMeetingTypeId,model.partyMeeting.partyMeetingType.type,model.path" +
       ",model.partyMeeting.partyMeetingId,sessionType.sessionTypeId,sessionType.type from PartyMeetingDocument model " +
       "  left join  model.partyMeetingSession partyMeetingSession  left join partyMeetingSession.sessionType sessionType " +
       " where model.partyMeeting.isActive = 'Y' and   partyMeetingSession.isDeleted = 'N' and ");
    
    if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
 	   if(locationTypeId == 2l){
	          sb.append("  model.partyMeeting.meetingAddress.state.stateId in (:locationValues) ");
	      }else if(locationTypeId == 4l){
           sb.append("  model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationValues) ");
         }else if(locationTypeId == 3l){
           sb.append("  model.partyMeeting.meetingAddress.district.districtId in (:locationValues)");
         }else if(locationTypeId == 5l){
           sb.append("  model.partyMeeting.meetingAddress.tehsil.tehsilId in (:locationValues)"); 
         }else if(locationTypeId == 6l){
           sb.append("  model.partyMeeting.meetingAddress.panchayat.panchayatId in (:locationValues)"); 
         }else if(locationTypeId==10l){
           sb.append("  model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationValues) "); 
         }else if(locationTypeId == 7l){
           sb.append("  model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId in (:locationValues)");
         }else if(locationTypeId == 8l){
           sb.append("  model.partyMeeting.meetingAddress.ward.constituencyId in (:locationValues)"); 
         }
     }
    if(partyMeetingMainTypeid != null && partyMeetingMainTypeid.longValue() > 0l){
 	   sb.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeid ");
    }
    if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0l){
 	   sb.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId = :partyMeetingTypeId ");
    }
    if(fromDate != null && toDate != null){
 	   sb.append(" and date(model.partyMeeting.startDate) between :fromDate and :toDate ");
    }
    //sb.append(" group by model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId,model.partyMeetingType.partyMeetingTypeId ");
    
    Query query = getSession().createQuery(sb.toString());
    
    if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
      query.setParameterList("locationValues", locationValues);
    }
    if(fromDate != null && toDate != null){
      query.setDate("fromDate", fromDate);
         query.setDate("toDate", toDate); 
    }
    if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0l){
 	   query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
    }
    if(partyMeetingMainTypeid != null && partyMeetingMainTypeid.longValue() > 0l){
 	   query.setParameter("partyMeetingMainTypeid", partyMeetingMainTypeid);
    }
    return query.list();
  }
public List<Object[]> getPartyMeetingMomDocumentByUserAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,String type,Long partyMeetingId){
	
    StringBuilder queryStr = new StringBuilder();
	    queryStr.append(" select " +
	    				" model.partyMeeting.partyMeetingId ");
	    if (type.equalsIgnoreCase("summary")) {
	    	queryStr.append(",count(distinct model.partyMeetingDocumentId)");
	    } else if (type.equalsIgnoreCase("details")) {
	    	queryStr.append(",model.partyMeetingDocumentId,model.path");
	    }
	    queryStr.append(" from PartyMeetingDocument model " +
	    				" where " +
	    				" model.partyMeeting.isActive='Y' and model.isDeleted='N'  ");
	
	   if(monthId != null && monthId > 0){
			  queryStr.append(" and month(model.partyMeeting.startDate)=:monthId ");	 
	  }
	  if(yearId != null && yearId > 0){
	 		queryStr.append(" and year(model.partyMeeting.startDate)=:yearId ");		 
	  }
	  if (partyMeetingId != null && partyMeetingId > 0) {
	 	 queryStr.append(" and model.partyMeeting.partyMeetingId=:partyMeetingId "); 
	  }
	 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
        queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
        queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
	    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
	   queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
	   queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
     }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
	   queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
	 }  
	 //getting meeting levelId
	 List<Long> partyMeetinLevelIds = getPartyMeetingLevelByRegionScope(userAccessLevelId);
	 if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
		 queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:partyMeetinLevelIds)");
	 }
	   queryStr.append(" and model.documentType = 'MINUTE'");
	   if (type.equalsIgnoreCase("summary")) {
	    	queryStr.append(" group by model.partyMeeting.partyMeetingId");
	   }
	
	 Query query = getSession().createQuery(queryStr.toString());
	
	 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	 }
	 if(monthId != null && monthId > 0){
		 query.setParameter("monthId", monthId);
	 }
	 	 if(yearId != null && yearId > 0){
	 		query.setParameter("yearId", yearId);		 
	 }
	  if (partyMeetingId != null && partyMeetingId > 0) {
	 		query.setParameter("partyMeetingId", partyMeetingId); 
	  }
	  if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
	 		 query.setParameterList("partyMeetinLevelIds", partyMeetinLevelIds);
	 }
	  
	  
  return query.list(); 	
 }
public Integer deletePartyMeetingMOMDocument(Long docId,Long loginUserId,Date updatedDate){
	Query query = getSession().createQuery("update PartyMeetingDocument model set model.isDeleted = 'Y',model.updatedTime=:updatedDate,model.updatedById=:updatedById where model.partyMeetingDocumentId =:docId");
	query.setParameter("updatedDate", updatedDate);
	query.setParameter("updatedById", loginUserId);
	query.setParameter("docId", docId);
	return query.executeUpdate();
}
public List<String> getMomDocuments(Long partyMeetingMomId){
	 StringBuilder queryStr = new StringBuilder();
	  queryStr.append("select model.path from PartyMeetingDocument model where model.partyMeetingMinuteId=:partyMeetingMomId");
	  Query query = getSession().createQuery(queryStr.toString());
	  query.setParameter("partyMeetingMomId", partyMeetingMomId);
	  return query.list();
}
public List<Long> getPartyMeetingLevelByRegionScope(Long userAccessLevelId) {
	   List<Long> partyMeetingLevelIds = new ArrayList<Long>();
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	    	partyMeetingLevelIds.add(1l);  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 partyMeetingLevelIds.add(2l); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
			 partyMeetingLevelIds.add(9l);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
			 partyMeetingLevelIds.add(3l); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
			 partyMeetingLevelIds.add(4l);   
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
			 partyMeetingLevelIds.add(5l);
			 partyMeetingLevelIds.add(6l);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
			 partyMeetingLevelIds.add(7l);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
			 partyMeetingLevelIds.add(8l);
		 }  
	    return partyMeetingLevelIds;
}
}
