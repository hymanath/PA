package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILightInstallationTargetDAO;
import com.itgrids.model.LightInstallationTarget;

@Repository
public class LightInstallationTargetDAO extends GenericDaoHibernate<LightInstallationTarget, Long> implements ILightInstallationTargetDAO {

	public LightInstallationTargetDAO() {
		super(LightInstallationTarget.class);
	}
	
	public List<Object[]> getLedTargetVendorWise(Date toDate) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select date(model.targetDate),model.lightsVendorId,model.ledTarget,model.ccmsBoxTarget from LightInstallationTarget model where date(model.targetDate)<=:todayDate group by date(model.targetDate),model.lightsVendorId order by date(model.targetDate) desc  ");
		 Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("todayDate", toDate);
		 return query.list();
	}
}
