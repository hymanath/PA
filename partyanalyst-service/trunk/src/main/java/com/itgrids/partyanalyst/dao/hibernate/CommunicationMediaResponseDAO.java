package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICommunicationMediaResponseDAO;
import com.itgrids.partyanalyst.model.CommunicationMediaResponse;

public class CommunicationMediaResponseDAO extends GenericDaoHibernate<CommunicationMediaResponse,Long> implements ICommunicationMediaResponseDAO{

	public CommunicationMediaResponseDAO()
	{
		super(CommunicationMediaResponse.class);
	}
	
	public List<Object[]> getIVRSummaryByTdpCadreId(Long tdpCadreId){
		
		Query query=getSession().createQuery(" " +
		  " select distinct model.communicationMediaResponseId," +
		  "        model.tdpCadre.tdpCadreId,model.mediaOptionsId,model.comments " +
		  " from  CommunicationMediaResponse model " +
		  " where model.tdpCadre.isDeleted='N' and model.isDeleted='false' and model.isValid='true' " +
		  "       and model.tdpCadre.tdpCadreId=:tdpCadreId ");
		query.setParameter("tdpCadreId",tdpCadreId);
		return query.list();
	}
	
	public List<Object[]> getQuestionAndoptionsByTdpCadreId(Long tdpCadreId){
		
		Query query=getSession().createSQLQuery("" +
		" select   distinct cmti.name as name,cmq.question as question ,cmq.media_option_type_id as optiontypeId,cmqo.media_options_id as optionId ,mo.options as options " +
		" from     communication_media_response cmr " +
		"           join communication_media_question cmq     on cmr.communication_media_question_id=cmq.communication_media_question_id " +
		"           join communication_media_type_info cmti   on cmq.communication_media_type_info_id=cmti.communication_media_type_info_id " +
		"           left  join communication_media_question_options cmqo on  cmq.communication_media_question_id=cmqo.communication_media_question_id" +
		"           left  join  media_options mo on cmqo.media_options_id=mo.media_options_id " +
		"where     cmr.tdp_cadre_id=:tdpCadreId  and cmr.is_deleted='false' and cmr.is_valid='true'").addScalar("name", Hibernate.STRING).
		addScalar("question", Hibernate.STRING).
		addScalar("optiontypeId", Hibernate.LONG).
		addScalar("optionId",  Hibernate.LONG).
		addScalar("options", Hibernate.STRING);
		query.setParameter("tdpCadreId",tdpCadreId);
		return query.list();
	}
	public List<Object[]> getIVRDetailsByTdpCadreId(Long tdpCadreId){
	
		Query query=getSession().createQuery(" " +
		  " select distinct model.communicationMediaResponseId," +
		  "        model.tdpCadre.tdpCadreId,model.communicationMediaQuestion.communicationMediaTypeInfo.name," +
		  "        model.mediaOptionsId,model.comments " +
		  " from  CommunicationMediaResponse model " +
		  " where model.tdpCadre.isDeleted='N' and model.isDeleted='false' and model.isValid='true' " +
		  "       and model.tdpCadre.tdpCadreId=:tdpCadreId ");
		query.setParameter("tdpCadreId",tdpCadreId);
		return query.list();
	}

}
