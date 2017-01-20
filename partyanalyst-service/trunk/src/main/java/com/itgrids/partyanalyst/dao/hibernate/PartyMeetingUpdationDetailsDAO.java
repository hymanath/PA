package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingUpdationDetailsDAO;
import com.itgrids.partyanalyst.model.PartyMeetingUpdationDetails;

public class PartyMeetingUpdationDetailsDAO extends GenericDaoHibernate<PartyMeetingUpdationDetails, Long> implements IPartyMeetingUpdationDetailsDAO{

	public PartyMeetingUpdationDetailsDAO() {
		super(PartyMeetingUpdationDetails.class);
	}
	  @SuppressWarnings("unchecked")
	public List<Object[]> getCommentsDetailsByPartyMeetingId(Long partyMeetingId){
		  
	      	Query query = getSession().createQuery(" select model.partyMeetingUpdationDetailsId,model.comment,model.name,model.insertedTime," +
	      			" model.mobileNo ,tdpCadre.tdpCadreId,tdpCadre.memberShipNo,tdpCadre.image " +
	      			" from PartyMeetingUpdationDetails model " +
	      			" left join model.tdpCadre tdpCadre where " +
	      			" model.partyMeetingId = :partyMeetingId and model.isDeleted= 'false' order by model.insertedTime desc");
	      	
	      	query.setParameter("partyMeetingId",partyMeetingId);
	      	return query.list();
	      }
	public List<Object[]> getCommentsAvailableByPartyMeetingId(List<Long> partyMeetingIds){
		  
      	Query query = getSession().createQuery(" select model.partyMeetingId,model.comment " +
      			" from PartyMeetingUpdationDetails model where " +
      			" model.partyMeetingId in(:partyMeetingIds) and model.isDeleted= 'false' ");
      	
      	query.setParameterList("partyMeetingIds",partyMeetingIds);
      	return query.list();
      }
}
