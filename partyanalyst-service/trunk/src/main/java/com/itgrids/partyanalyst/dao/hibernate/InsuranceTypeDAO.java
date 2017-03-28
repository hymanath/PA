package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IInsuranceTypeDAO;
import com.itgrids.partyanalyst.model.InsuranceType;

public class InsuranceTypeDAO extends GenericDaoHibernate<InsuranceType, Long> implements IInsuranceTypeDAO{

	public InsuranceTypeDAO() {
		super(InsuranceType.class);
		
	}
	
	public List<Object[]> getAllInsuranceType(){
		
		Query query=getSession().createQuery("select model.insuranceTypeId,model.type from InsuranceType model ");
		
		return query.list();
	}

}
