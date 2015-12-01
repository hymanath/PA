package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICommunicationMediaResponseDAO;
import com.itgrids.partyanalyst.model.CommunicationMediaResponse;

public class CommunicationMediaResponseDAO extends GenericDaoHibernate<CommunicationMediaResponse,Long> implements ICommunicationMediaResponseDAO{

	public CommunicationMediaResponseDAO()
	{
		super(CommunicationMediaResponse.class);
	}
	
	public List<Object[]> getIVRSummaryByTdpCadreId(Long tdpCadreId){
		/*select  distinct cmr.communication_media_response_id,
		                 cmr.tdp_cadre_id,cmr.media_options_id,cmr.comments
        from    communication_media_response cmr
                inner join tdp_cadre tc where cmr.tdp_cadre_id=tc.tdp_cadre_id
                and  tc.is_deleted='N' and cmr.is_deleted='false' and cmr.is_valid='true' 
                and cmr.tdp_cadre_id=7266261; */
		Query query=getSession().createQuery(" " +
		  " select distinct model.communicationMediaResponseId," +
		  "        model.tdpCadre.tdpcadreId,model.mediaOptionsId,model.comments " +
		  " from  CommunicationMediaResponse model " +
		  " where model.tdpCadre.isDeleted='N' and model.isDeleted='false' and midel.isValid='true' " +
		  "       and model.tdpCadre.tdpCadreId=:tdpCadreId ");
		query.setParameter("tdpCadreId",tdpCadreId);
		return query.list();
	}

}
