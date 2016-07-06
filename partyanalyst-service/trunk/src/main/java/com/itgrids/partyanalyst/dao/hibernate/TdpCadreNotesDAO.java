package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreNotesDAO;
import com.itgrids.partyanalyst.model.TdpCadreNotes;

public class TdpCadreNotesDAO extends GenericDaoHibernate<TdpCadreNotes, Long> implements ITdpCadreNotesDAO{

	public TdpCadreNotesDAO() {
		super(TdpCadreNotes.class);
		
	}
public List<Object[]> getCadreNotesInformation(Long tdpCadreId,Integer startIndex,Integer maxIndex,Long userId){
		
		Query query = getSession().createQuery(" select model.tdpCadreNotesId,model.notes,model.user.userName,model.insertedTime,model.updatedTime" +
				" from TdpCadreNotes model " +
				" where model.tdpCadreId=:tdpCadreId and " +
				" model.insertedBy=:userId and model.isDeleted = 'false' order by model.insertedTime desc");
		if(startIndex!=null) 
		query.setFirstResult(startIndex);
		if(maxIndex != null)
		 query.setMaxResults(maxIndex);
		 query.setParameter("tdpCadreId", tdpCadreId);
		 query.setParameter("userId", userId);
		return query.list();
	} 

public Long getTotalCadreNotesInformation(Long tdpCadreId){
	
	Query query = getSession().createQuery(" select count(model.tdpCadreNotesId) from TdpCadreNotes model " +
			" where model.tdpCadreId=:tdpCadreId and model.isDeleted = 'false' ");
	 query.setParameter("tdpCadreId", tdpCadreId);
	return (Long) query.uniqueResult();
}

public Integer updateCadreNotesInformationData(Long cadreNotes){
	
	Query query = getSession().createQuery("update TdpCadreNotes model set model.isDeleted = 'true'" +
											" where model.tdpCadreNotesId = :cadreNotes");
	query.setParameter("cadreNotes", cadreNotes);
	return query.executeUpdate();
}
public Integer updateCadreNotesAllData(Long primaryTdpCadrenotesId){
	
	Query query = getSession().createQuery("update TdpCadreNotes model set model.isDeleted = 'true'" +
											" where model.tdpCadreNotesId = :primaryTdpCadrenotesId");
	
	query.setParameter("primaryTdpCadrenotesId", primaryTdpCadrenotesId);
	return query.executeUpdate();
}
}
