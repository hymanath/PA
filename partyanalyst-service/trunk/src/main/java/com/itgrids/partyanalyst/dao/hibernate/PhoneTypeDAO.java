package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPhoneTypeDAO;
import com.itgrids.partyanalyst.model.PhoneType;
import org.hibernate.Query;
//import java.util.List;

public class PhoneTypeDAO extends GenericDaoHibernate<PhoneType, Long> implements IPhoneTypeDAO{
	
	public PhoneTypeDAO () {
		super(PhoneType.class);
	}
	
	public Object getPhoneTypeNameByPhoneTypeId(Long phoneTypeId){
		

		Query query = getSession().createQuery("select model.type from PhoneType model where model.phoneTypeId=?");
		query.setParameter(0, phoneTypeId);
		
		return query.list();
	
	}
	
	public List<Object[]> getAllPhoneTypes(){
		
		Query query = getSession().createQuery("select model.phoneTypeId , model.type from PhoneType model");
		return query.list();
		
		
	}
}
