package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dto.EventDocumentVO;
import com.itgrids.partyanalyst.model.ActivityInfoDocument;

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
				str.append(" and model.userAddress.district.districtId <= 10 ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.district.districtId >= 11 ");
			}
		}
	
			
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());
		query.setParameter("activityDocumentId", inputVO.getActivityId());
		if(inputVO.getLocationScope().equalsIgnoreCase("village"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
			query.setParameter("locationValue",inputVO.getLocationValue());
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}

}
