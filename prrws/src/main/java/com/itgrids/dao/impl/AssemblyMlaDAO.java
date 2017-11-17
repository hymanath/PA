package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IAssemblyMlaDAO;
import com.itgrids.model.AssemblyMla;

@Repository
public class AssemblyMlaDAO extends GenericDaoHibernate<AssemblyMla, Long> implements IAssemblyMlaDAO {
	
		@Autowired
		SessionFactory sessionFactory;
		
		AssemblyMlaDAO(){
		super(AssemblyMla.class);
		} 
		
		public List<Object[]> getAllConstituencyDetails(Long constituencyId){
		    StringBuilder sb = new StringBuilder();
		    sb.append(" select model.districtId, "             //0 district Id
		    		+ " model.district.districtName,  "       //1 district name
		    		+ " model.mlaName, "
		    		+ " model.constituency.name, "                      //2 mla name
		    		+ " model.party "
		    		+ " from AssemblyMla model ");
		    if(constituencyId != null)
		    	sb.append(" where model.constituencyId = :constituencyId");
		    
		    Query query = getSession().createQuery(sb.toString());
		    if(constituencyId != null)
		    	query.setParameter("constituencyId", constituencyId);
		   
		    return query.list();
		}

}
