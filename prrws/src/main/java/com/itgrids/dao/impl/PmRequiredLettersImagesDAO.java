package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRequiredLettersImagesDAO;
import com.itgrids.model.PmRequiredLettersImages;

@Repository
public class PmRequiredLettersImagesDAO extends GenericDaoHibernate<PmRequiredLettersImages, Long> implements IPmRequiredLettersImagesDAO {

	@Autowired
	SessionFactory sessionFactory;

	public PmRequiredLettersImagesDAO() {
		super(PmRequiredLettersImages.class);
	}
	
	public List<Object[]> getDesignationWiseImages(List<Long> ofcrDesigIds,String letterType,Long officerId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.pmOfficerDesignation.pmOfficerDesignationId,model.imageType,model.filePath from PmRequiredLettersImages model  where model.isDeleted ='N' ");
		
		if(ofcrDesigIds != null && ofcrDesigIds.size() >0){
			sb.append("  and model.pmOfficerDesignation.pmOfficerDesignationId in (:ofcrDesigIds) ");
		}
		if(officerId != null && officerId.longValue()>0l){
			sb.append("  and model.pmOfficer.pmOfficerId = :officerId ");
		}
		if(letterType != null && !letterType.isEmpty()){
			sb.append("  and model.letterType = :letterType ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(ofcrDesigIds != null && ofcrDesigIds.size() >0){
			query.setParameterList("ofcrDesigIds", ofcrDesigIds);
		}
		if(letterType != null && !letterType.isEmpty()){
			query.setParameter("letterType", letterType);
		}
		if(officerId != null && officerId.longValue()>0l){
			query.setParameter("officerId", officerId);
		}
		return query.list();
	}
	
}