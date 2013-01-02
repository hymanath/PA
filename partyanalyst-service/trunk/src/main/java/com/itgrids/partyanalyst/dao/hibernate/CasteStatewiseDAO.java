package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICasteStatewiseDAO;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.utils.IConstants;

public class CasteStatewiseDAO extends GenericDaoHibernate<CasteState, Long> implements 
ICasteStatewiseDAO {
	public CasteStatewiseDAO(){
		super(CasteState.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getStatewiseCastNames(Long casteCategoryGroupId){
		
		Query query = getSession().createQuery("select model.caste.casteId,model.caste.casteName from CasteStatewise model where model.casteCategoryGroup.casteCategoryGroupId = ?");
		query.setParameter(0,casteCategoryGroupId);
			
		return query.list();
	}
	
	public List<Object[]> getCasteNamesByAutoPopulate(Long stateId,String searchString) {
		String cName = ""+searchString+"%";
		Query queryObject = getSession().createQuery("select model.caste.casteId,model.caste.casteName from CasteStatewise model where model.state.stateId=? and model.caste.casteName like ?");
		queryObject.setLong(0,stateId);
		queryObject.setString(1, cName);
	
		return queryObject.list();		
	}
	
	public List<Object[]> getAllCasteDetails(){
		
		Query query = getSession().createQuery("select model.caste.casteId , model.caste.casteName from CasteStatewise model");
		
		return query.list();
		
		
	}
/*	
public List<Object[]> getAllCasteInfoDetails(){
		
		//Query query = getSession().createQuery("select model.casteId , model.casteName from Caste model");
		return query.list();
		
		
	}*/
	
}
