package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrMobileDAO;
import com.itgrids.partyanalyst.model.IvrMobile;

public class IvrMobileDAO extends GenericDaoHibernate<IvrMobile, Long> implements IIvrMobileDAO{

	public IvrMobileDAO() {
		super(IvrMobile.class);
		
	}

	public List<Long> getMobilenos(Long scopeId,Long location,int maxIndex) 
	{
		StringBuilder str = new StringBuilder(); 
		str.append("select distinct model.mobileNo from IvrMobile model where");
		if(scopeId == 1)
		 str.append(" model.constituency.district.districtId =:location")	;
		 else
		 str.append(" model.constituency.constituencyId =:location");
		 str.append(" and model.mobileNo is not null and length(model.mobileNo) = 10 and model.mobileNo <> '9999999999' order by rand()");
		 Query query = getSession().createQuery(str.toString());
		 query.setParameter("location", location);
		 if(maxIndex > 0)
		 {
			
			 query.setMaxResults(maxIndex);	 
		 }
		 return query.list();
		 
	}
	
}
