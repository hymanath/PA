package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRequiredFileFormatTextDAO;
import com.itgrids.model.PmRequiredFileFormatText;

@Repository
public class PmRequiredFileFormatTextDAO extends GenericDaoHibernate<PmRequiredFileFormatText, Long> implements IPmRequiredFileFormatTextDAO{
	@Autowired
	SessionFactory sessionFactory;
	
	public PmRequiredFileFormatTextDAO() {
		super(PmRequiredFileFormatText.class);
	}
	
	public String getCoverLetterMessage(List<Long> ofcrDesigIds){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.formatText from PmRequiredFileFormatText model where model.isDeleted = 'N' ");
		/*if(ofcrDesigIds != null && ofcrDesigIds.size() >0){
			sb.append("  and model.pmOfficerDesignation.pmOfficerDesignationId in (:ofcrDesigIds) ");
		}*/
		
		Query query = getSession().createQuery(sb.toString());
		/*if(ofcrDesigIds != null && ofcrDesigIds.size() >0){
			query.setParameterList("ofcrDesigIds", ofcrDesigIds);
		}*/
		return (String)query.uniqueResult();
		
	}

}
