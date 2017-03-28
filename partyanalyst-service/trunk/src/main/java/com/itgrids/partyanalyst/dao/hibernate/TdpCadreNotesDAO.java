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
	
	StringBuilder sb = new StringBuilder();
	sb.append(" select model.tdpCadreNotesId,model.notes,model.user.userName,model.insertedTime,model.updatedTime,model.user.lastName,model.user.userId " +
				" from TdpCadreNotes model " +
				" where model.tdpCadreId=:tdpCadreId  " );
	if(userId != null && userId > 0l)
		sb.append(" and model.insertedBy=:userId ");
	sb.append(" and model.isDeleted = 'false' order by model.insertedTime desc");
		
		Query query = getSession().createQuery(sb.toString());
		if(startIndex!=null) 
		query.setFirstResult(startIndex);
		if(maxIndex != null)
		 query.setMaxResults(maxIndex);
		
		if(userId != null && userId > 0l)
			 query.setParameter("userId", userId);
		query.setParameter("tdpCadreId", tdpCadreId);
		
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
