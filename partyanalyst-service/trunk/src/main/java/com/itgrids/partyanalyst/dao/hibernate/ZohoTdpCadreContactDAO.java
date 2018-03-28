package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IZohoTdpCadreContactDAO;
import com.itgrids.partyanalyst.model.ZohoTdpCadreContact;

public class ZohoTdpCadreContactDAO extends GenericDaoHibernate<ZohoTdpCadreContact, Long> implements IZohoTdpCadreContactDAO{

	public ZohoTdpCadreContactDAO() {
		super(ZohoTdpCadreContact.class);
	}
	
	public List<Long> getTdpCadreId(String zohoContactId){
		Query query = getSession().createQuery(" select model.tdpCadreId " +
				" from ZohoTdpCadreContact model " +
				" where model.zohoContactId=:zohoContactId and model.isValid='Y' ");
		query.setParameter("zohoContactId", zohoContactId);
		return query.list();
	}
	
	public List<Object[]> getZohoContactDetails(String zohoUserId){
		
		Query query = getSession().createQuery("select model.tdpCadreId,model.userId from ZohoTdpCadreContact model " +
				" where model.zohoUserId =:zohoUserId" +
				" and model.isValid ='Y' ");
		
		query.setParameter("zohoUserId", zohoUserId);
		
		return query.list();
	}
	public List<Long> getTdpCadresIdOfContacts(List<String> zohoContactIds){
		Query query = getSession().createQuery(" select model.tdpCadreId " +
				" from ZohoTdpCadreContact model " +
				" where model.zohoContactId in (:zohoContactIds) and model.isValid='Y' ");
		query.setParameterList("zohoContactIds", zohoContactIds);
		return query.list();
	}
	
	public List<Object[]> getAlertCadreTypeInfo(String membershipId){
		Query query = getSession().createQuery(" select model.tdpCadreId,model.tdpCadre.memberShipNo,model1.alertCadreType.alertCadreTypeId," +
				" model1.alertCadreType.typeName,model1.isAlertCreate " +
				" from ZohoTdpCadreContact model,AlertCadreTypeRelation model1 " +
				" where model.tdpCadreId = model1.tdpCadreId " +
				" and model.isValid='Y' " +
				" and model.tdpCadre.isDeleted ='N'" +
				" and model.tdpCadre.memberShipNo =:membershipId ");
		query.setParameter("membershipId", membershipId.trim());
		return query.list();
	} 
}
