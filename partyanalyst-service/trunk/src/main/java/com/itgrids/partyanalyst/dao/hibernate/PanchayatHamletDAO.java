package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.PanchayatHamlet;

public class PanchayatHamletDAO extends GenericDaoHibernate<PanchayatHamlet,Long> implements IPanchayatHamletDAO{

	public PanchayatHamletDAO()
	{
		super(PanchayatHamlet.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHamletsOfAPanchayat(Long panchayatId)
	{
		return getHibernateTemplate().find("select model.hamlet.hamletId,model.hamlet.hamletName from PanchayatHamlet model where model.panchayat.panchayatId = ? order by model.hamlet.hamletName",panchayatId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCount(Long panchayatId)
	{
		return getHibernateTemplate().find("select");
	}
	
	public List<Panchayat> getPanchayatByHamletId(Long hamletId){
		
		
		return getHibernateTemplate().find("select model.panchayat from PanchayatHamlet model where model.hamlet.hamletId = ? ",hamletId);

		
	}
	
	
	public List<Long> getHamletsOfPanchayitis(List<Long> panchayitIds){
		
		
		Query query = getSession().createQuery("select model.hamlet.hamletId from PanchayatHamlet model where model.panchayat.panchayatId in(:panchayitIds)");
		
		query.setParameterList("panchayitIds", panchayitIds);
		
		return query.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<Object> getHamletsCountOfAPanchayat(Long panchayatId)
	{
		return getHibernateTemplate().find("select count(distinct model.hamlet.hamletId) from PanchayatHamlet model where model.panchayat.panchayatId = ?   ",panchayatId);
	}
	
	public List<Object> getAssignedVotersCountDetails(Long panchayatId)
	{
		return getHibernateTemplate().find("select count(UV.voter.voterId) from PanchayatHamlet PH,UserVoterDetails UV where PH.hamlet.hamletId =UV.hamlet.hamletId and PH.panchayat.panchayatId = ? ",panchayatId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHamletsByPanchayatsList(List<Long> panchayatsList)
	{
		Query query = getSession().createQuery("select model.panchayat.panchayatId,model.panchayat.panchayatName,model.hamlet.hamletId, model.hamlet.hamletName from PanchayatHamlet model where model.panchayat.panchayatId in (:panchayatsList) order by model.panchayat.panchayatName,model.hamlet.hamletName ");
		query.setParameterList("panchayatsList",panchayatsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHamletsListByConstituencyId(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select distinct model.hamlet.hamletId,model.hamlet.hamletName from PanchayatHamlet model, Booth model2 where model.panchayat.panchayatId =model2.panchayat.panchayatId and  " +
				" model2.constituency.constituencyId =:constituencyId and model2.publicationDate.publicationDateId =:publicationDateId  )");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	

}
