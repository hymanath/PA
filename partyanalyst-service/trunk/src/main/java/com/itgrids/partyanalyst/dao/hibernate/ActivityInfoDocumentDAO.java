package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dto.EventDocumentVO;
import com.itgrids.partyanalyst.model.ActivityInfoDocument;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityInfoDocumentDAO extends GenericDaoHibernate<ActivityInfoDocument, Long> implements IActivityInfoDocumentDAO{

	public ActivityInfoDocumentDAO() {
		super(ActivityInfoDocument.class);
		
	}
	
	public List<Object[]> getEventDocuments(EventDocumentVO inputVO,Date startDate,Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.activityDocument.documentName,model.activityDocument.path,model.day" +
				"  from ActivityInfoDocument model where model.activityDocument.activityScopeId = :activityDocumentId");
		if(inputVO.getDay() > 0)
			str.append(" and model.day = :day");
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			if(inputVO.getLocationValue() == 36)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.ward.constituencyId = :locationValue");
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());
		query.setParameter("activityDocumentId", inputVO.getActivityId());
		if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(inputVO.getMaxIndex() > 0)
		{
			query.setFirstResult(inputVO.getStartIndex());
			query.setMaxResults(inputVO.getMaxIndex());
		}
			
		return query.list();
	}
	public Long getEventDocumentsCount(EventDocumentVO inputVO,Date startDate,Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.activityDocument.activityDocumentId)" +
				"  from ActivityInfoDocument model where model.activityDocument.activityScopeId = :activityDocumentId");
		if(inputVO.getDay() > 0)
			str.append(" and model.day = :day");
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			if(inputVO.getLocationValue() == 36)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.ward.constituencyId = :locationValue");
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());
		query.setParameter("activityDocumentId", inputVO.getActivityId());
		if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getLocations(EventDocumentVO inputVO,Date startDate,Date endDate,String type)
	{
		StringBuilder str = new StringBuilder();
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			str.append(" select distinct model.userAddress.district.districtId,model.userAddress.district.districtName ");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
		{
			str.append(" select distinct model.userAddress.constituency.constituencyId,model.userAddress.constituency.name ");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.MANDAL))
		{
			str.append(" select distinct model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		{
			str.append(" select distinct model.userAddress.localElectionBody.localElectionBodyId, model.userAddress.localElectionBody.name ");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
		{
			str.append(" select  distinct model.userAddress.panchayat.panchayatId, model.userAddress.panchayat.panchayatName ");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
		{
			str.append(" select  model.userAddress.ward.constituencyId, model.userAddress.ward.name ");
		}
		str.append(" from ActivityInfoDocument model where model.activityDocument.activityDate is not null ");
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			if(inputVO.getLocationValue() == 36)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		/*if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");*/
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		if(inputVO.getActivityId() > 0)
		{
			str.append(" and model.activityDocument.activityScopeId = :activityScopeId");
		}
		Query query = getSession().createQuery(str.toString());
		/*if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());*/
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(inputVO.getActivityId() > 0)
			query.setParameter("activityScopeId",inputVO.getActivityId());
		return query.list();
	}
	
	
	public List<Object[]> getLocationWiseImageCount(EventDocumentVO inputVO,Date startDate,Date endDate,String type)
	{
		StringBuilder str = new StringBuilder();
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			str.append(" select distinct model.userAddress.district.districtId,model.userAddress.district.districtName ");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
		{
			str.append(" select distinct model.userAddress.constituency.constituencyId,model.userAddress.constituency.name ");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.MANDAL))
		{
			str.append(" select distinct model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		{
			str.append(" select distinct model.userAddress.localElectionBody.localElectionBodyId, model.userAddress.localElectionBody.name ");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
		{
			str.append(" select  distinct model.userAddress.panchayat.panchayatId, model.userAddress.panchayat.panchayatName");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
		{
			str.append(" select  distinct model.userAddress.ward.constituencyId, model.userAddress.ward.name");
		}
		str.append(" ,count(model.activityDocument.activityDocumentId)");
		str.append(" from ActivityInfoDocument model where model.activityDocument.activityDate is not null ");
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		if(inputVO.getActivityId() > 0)
		{
			str.append(" and model.activityDocument.activityScopeId = :activityScopeId");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			if(inputVO.getLocationValue() == 36)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue group by model.userAddress.district.districtId ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue group by model.userAddress.district.districtId ");
			}
		}
		
		
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue group by model.userAddress.constituency.constituencyId ");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.MANDAL))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue group by model.userAddress.tehsil.tehsilId ");
		
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue group by model.userAddress.localElectionBody.localElectionBodyId ");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue group by model.userAddress.panchayat.panchayatId");
	
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue group by model.userAddress.ward.constituencyId ");
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(inputVO.getActivityId() > 0)
			query.setParameter("activityScopeId",inputVO.getActivityId());
		return query.list();
	}
	
	public Integer deleteEventUploadFilebyActivityInfoDocId(Long acitivityInfoDocId)
	{
		Query query = getSession().createQuery(" update ActivityInfoDocument model set model.isDeleted = 'Y' where model.activityInfoDocumentId =:acitivityInfoDocId ");
		
		query.setParameter("acitivityInfoDocId", acitivityInfoDocId);
		return query.executeUpdate();
	}
	
	
	public List<Object[]> getAvailableDates(EventDocumentVO inputVO,Date startDate,Date endDate,String type)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.day,date(model.activityDocument.activityDate) ");
		str.append(" from ActivityInfoDocument model where model.activityDocument.activityDate is not null and model.day is not null ");
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			if(inputVO.getLocationValue() == 36)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		/*if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");*/
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.ward.constituencyId = :locationValue");
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		if(inputVO.getActivityId() > 0)
		{
			str.append(" and model.activityDocument.activityScopeId = :activityScopeId");
		}
		Query query = getSession().createQuery(str.toString());
		/*if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());*/
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") || inputVO.getLocationScope().equalsIgnoreCase("village") )
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(inputVO.getActivityId() > 0)
			query.setParameter("activityScopeId",inputVO.getActivityId());
		return query.list();
	}
	

}
