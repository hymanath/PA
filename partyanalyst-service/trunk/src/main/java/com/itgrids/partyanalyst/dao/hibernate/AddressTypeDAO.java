package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.itgrids.partyanalyst.dao.IAddressTypeDAO;
import com.itgrids.partyanalyst.model.AddressType;


public class AddressTypeDAO extends GenericDaoHibernate<AddressType, Long> implements IAddressTypeDAO{
	
	
	public AddressTypeDAO() {
		super(AddressType.class);
	}
	
	public List getAddressTypeByAddressTypeId(Long addressTypeId){
		
		Query query = getSession().createQuery("select model.type from AddressType model where model.addressTypeId=?");
		query.setParameter(0,addressTypeId);
		return query.list();
	
	}
	
	public List<Object[]> getAllAddressTypes(){
		
		Query query = getSession().createQuery("select model.addressTypeId , model.type from AddressType model");
		return query.list();
		
		
	}
}
