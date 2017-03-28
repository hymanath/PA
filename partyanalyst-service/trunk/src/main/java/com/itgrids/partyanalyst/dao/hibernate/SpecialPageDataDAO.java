package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISpecialPageDataDAO;
import com.itgrids.partyanalyst.model.SpecialPageData;

public class SpecialPageDataDAO  extends GenericDaoHibernate<SpecialPageData, Long> implements ISpecialPageDataDAO {
	
	public SpecialPageDataDAO(){
		super(SpecialPageData.class);
		
	}
	
	
	public List<String> getSpecialPageDataBySpecialPageId(Long specialPageId){
		
		
		Query query = getSession().createQuery("select model.specialPageData from SpecialPageData model where model.specialPage.specialPageId = ?");
		
		query.setParameter(0, specialPageId);
		
		return query.list();
		
		
	}
	
	public List<SpecialPageData> getSpecialPageDataObjectBySpecialPageId(Long specialPageId){
		
         Query query = getSession().createQuery("select model from SpecialPageData model where model.specialPage.specialPageId = ?");
		
		query.setParameter(0, specialPageId);
		
		return query.list();
		
	}

	
}
