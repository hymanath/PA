package com.itgrids.partyanalyst.dao.hibernate;

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
	
	public List<Object[]> getEventDocuments(EventDocumentVO inputVO)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.activityDocument.documentName,model.activityDocument.path,model.day" +
				"  from ActivityInfoDocument model where model.activityScopeId = :activityDocumentId");
		if(inputVO.getDay() > 0)
			str.append(" and model.day = :day");
		if(inputVO.getLocationScopeId() == 3)
			str.append(" and model.userAddress.district.districtId = :locationValue");
		if(inputVO.getLocationScopeId() == 4)
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScopeId() == 5)
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		if(inputVO.getLocationScopeId() == 6)
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");
		if(inputVO.getLocationScopeId() == 7)
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("locationValue", inputVO.getLocationValue());
		if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());
		query.setParameter("activityDocumentId", inputVO.getActivityId());
		return query.list();
	}

}
