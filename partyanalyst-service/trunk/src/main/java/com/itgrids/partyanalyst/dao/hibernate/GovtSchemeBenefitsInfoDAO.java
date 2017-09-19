package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtSchemeBenefitsInfoDAO;
import com.itgrids.partyanalyst.model.GovtSchemeBenefitsInfo;

public class GovtSchemeBenefitsInfoDAO  extends GenericDaoHibernate<GovtSchemeBenefitsInfo, Long> implements IGovtSchemeBenefitsInfoDAO{

	public GovtSchemeBenefitsInfoDAO() {
		super(GovtSchemeBenefitsInfo.class);
		
	}

	public List<Object[]> getGovtSchemeWiseBenefitMemberCount(Long locationType, Long locationValue) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.govtSchemes.govtSchemesId," +
						" model.govtSchemes.schemeName," +
				        " model.grivenaceCount " +
				        " from " +
				        " GovtSchemeBenefitsInfo model " +
				        " where ");
		if(locationType != null && locationType.longValue()>0l){
			queryStr.append(" model.locationScopeId =:locationType and ");
		}
		if(locationValue != null && locationValue.longValue()>0l){
			queryStr.append(" model.locationValue =:locationValue ");
		}
		queryStr.append(" group by model.govtSchemes.govtSchemesId");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(locationType != null && locationType.longValue()>0l){
			query.setParameter("locationType", locationType);
		}
		if(locationValue != null && locationValue.longValue()>0l){
			query.setParameter("locationValue", locationValue);
		}
		return query.list();
	}
}
