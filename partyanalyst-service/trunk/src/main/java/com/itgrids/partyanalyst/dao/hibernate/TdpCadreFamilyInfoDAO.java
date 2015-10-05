package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreFamilyInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreFamilyInfo;


public class TdpCadreFamilyInfoDAO extends GenericDaoHibernate<TdpCadreFamilyInfo, Long> implements ITdpCadreFamilyInfoDAO{

	public TdpCadreFamilyInfoDAO() {
		super(TdpCadreFamilyInfo.class);
	}
	
	public Integer moveFamilyInfoToHistoryByCadre(Long tdpCadreId)
	{
		
		//Query query = getSession().createQuery("delete from TdpCadreFamilyInfo model where model.tdpCadre.tdpCadreId =:tdpCadreId and model.isDeleted = 'N' ");
		Query query = getSession().createQuery(" update TdpCadreFamilyInfo model set model.isDeleted = 'H' where model.tdpCadreId =:tdpCadreId and model.isDeleted = 'N' ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.executeUpdate();
	}

	
	
	public List<TdpCadreFamilyInfo> getCadreFamilyDetailsBytdpCadreId(Long tdpCadreId)
	{

		Query query = getSession().createQuery("select model from TdpCadreFamilyInfo model " +
				"  where model.tdpCadreId = :tdpCadreId and model.isDeleted = 'N' ");
		query.setParameter("tdpCadreId", tdpCadreId);
		
		return query.list();
	}
	
	public List<TdpCadreFamilyInfo> getCadresFamilyDetailsBytdpCadreIdList(Set<Long> tdpCadreIdsSet)
	{

		Query query = getSession().createQuery("select model from TdpCadreFamilyInfo model " +
				"  where model.tdpCadreId in (:tdpCadreIdsSet) and model.isDeleted = 'N' and model.relationId = 13 ");// self relationShip members
		query.setParameterList("tdpCadreIdsSet", tdpCadreIdsSet);
		
		return query.list();
	}
	public List<Long> getFamilyUpdatedOrNot(List<Long> tdpCadreIds){
		
		Query query=getSession().createQuery("select distinct model.tdpCadreId from TdpCadreFamilyInfo model  where model.isDeleted = 'N' and model.tdpCadreId in (:tdpCadreIds)");
		query.setParameterList("tdpCadreIds",tdpCadreIds);
		return query.list();
	}
	
	public List<Object[]> getWhatsAppAndFbDetailsOfCadre(Long tdpCadreId){
		
		Query query=getSession().createQuery(" select model.tdpCadreFamilyInfoId,model.whatsappStatus,model.facebookUrl" +
				" from TdpCadreFamilyInfo model where " +
				" model.tdpCadreId = :tdpCadreId" +
				" and model.isDeleted ='N' and  model.relationId = 13 ");
		
		query.setParameter("tdpCadreId",tdpCadreId);
		return query.list();
		
	}
	
}
