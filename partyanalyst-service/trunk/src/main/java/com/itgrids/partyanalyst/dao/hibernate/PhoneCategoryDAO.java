package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPhoneCategoryDAO;
import com.itgrids.partyanalyst.model.PhoneCategory;



public class PhoneCategoryDAO  extends GenericDaoHibernate<PhoneCategory, Long> implements IPhoneCategoryDAO{

	public PhoneCategoryDAO() {
		super(PhoneCategory.class);
		// TODO Auto-generated constructor stub
	}

	public Object getPhoneCategoryNameByPhoneCategoryId(Long phoneCategoryId) {
		
		Query query = getSession().createQuery("select model.phoneCategoryName from PhoneCategory model where model.phoneCategoryId=?");
		query.setParameter(0, phoneCategoryId);
		
		return query.list();
	}
	
	public List<Object[]> getAllPhoneCategorys(){
		
		Query query = getSession().createQuery("select model.phoneCategoryId , model.phoneCategoryName from PhoneCategory model");
		return query.list();
		
		
	}
}
