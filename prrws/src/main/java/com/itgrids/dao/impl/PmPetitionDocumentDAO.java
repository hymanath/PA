package com.itgrids.dao.impl;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmPetitionDocumentDAO;
import com.itgrids.model.PmPetitionDocument;

@Repository
public class PmPetitionDocumentDAO extends GenericDaoHibernate<PmPetitionDocument, Long> implements IPmPetitionDocumentDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public PmPetitionDocumentDAO() {
		super(PmPetitionDocument.class);
	}
	public List<Object[]> getPmPetitionDocumentByPetition(Long petitionId){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.pmPetitionDocumentId, model.document.path from PmPetitionDocument model where model.petitionId =:petitionId and model.isDeleted='N' " +
				" group by model.pmPetitionDocumentId ");
		Query query =getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	
	public List<Long> getPmPetitionDocumentIds(Long petitionId){
		StringBuilder str = new StringBuilder();
		str.append("select  distinct model.pmPetitionDocumentId from PmPetitionDocument model where model.isDeleted='N' and  model.petitionId =:petitionId  and model.isDeleted='N'  ");
		Query query =getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	public int updatePmpetitionDocuments(List<Long> petitiionDocIds,Long userId ){
		StringBuilder str = new StringBuilder();
		str.append(" update PmPetitionDocument model set model.isDeleted ='Y', model.updatedUserId =:updatedUserId where model.pmPetitionDocumentId in (:petitiionDocIds) " );
		Query query =getSession().createQuery(str.toString());
		query.setParameterList("petitiionDocIds", petitiionDocIds);
		query.setParameter("updatedUserId", userId);
		return query.executeUpdate();
	}
	
	public List<Object[]> getWorkDocuments(Set<Long> petitionIds){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.pmPetitionDocumentId, model.document.path,model.petitionId  from PmPetitionDocument model " +
				"where  model.isDeleted='N' ");
		
		if(petitionIds != null && petitionIds.size()>0)
			str.append(" and model.petitionId in (:petitionIds) ");
		Query query =getSession().createQuery(str.toString());
		if(petitionIds != null && petitionIds.size()>0)
			query.setParameterList("petitionIds", petitionIds);
		return query.list();
	}
}
