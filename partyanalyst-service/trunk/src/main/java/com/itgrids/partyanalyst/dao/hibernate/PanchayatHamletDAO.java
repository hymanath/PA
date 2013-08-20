package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.PanchayatHamlet;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsListByMandalIdsList(List<Long> mandalIdsList)
	{
		Query query = getSession().createQuery(" select distinct model.panchayat.panchayatId,model.panchayat.panchayatName,model.panchayat.tehsil.tehsilId from PanchayatHamlet model where model.panchayat.tehsil.tehsilId in(:tehsilId) ");
		query.setParameterList("tehsilId", mandalIdsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getHamletsCountByLocationId(String type,Long constituencyId,Long id,Long publicationDateId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select count(distinct model.hamlet.hamletId) from PanchayatHamlet model, Booth model2 where model.panchayat.panchayatId = model2.panchayat.panchayatId and ");
		stringBuilder.append(" model2.constituency.constituencyId =:constituencyId and model2.publicationDate.publicationDateId =:publicationDateId ");
		if(type.equalsIgnoreCase(IConstants.MANDAL))
			stringBuilder.append(" and model.panchayat.tehsil.tehsilId =:id ");
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			stringBuilder.append(" and model.panchayat.panchayatId =:id ");
		
		Query query = getSession().createQuery(stringBuilder.toString());
		
		query.setParameter("id", id);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
	}
	public List<Object[]> getHamletsOfPanchayats(List<Long> panchayitIds){
		
		
		Query query = getSession().createQuery("select model.hamlet.hamletId,model.panchayat.panchayatId from PanchayatHamlet model where model.panchayat.panchayatId in(:panchayitIds)");
		
		query.setParameterList("panchayitIds", panchayitIds);
		
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHamletDetailsByPanchayatIds(List<Long> panchayatIds,Long publicationId,Long userId){
		Query query = getSession().createQuery("select distinct ph.panchayat.panchayatId,ph.panchayat.panchayatName,ph.hamlet.hamletId,ph.hamlet.hamletName,count(uvd.voter.voterId) "+
				"from UserVoterDetails uvd,PanchayatHamlet ph,BoothPublicationVoter bpv where uvd.user.userId = :userId and"+
				"  uvd.hamlet.hamletId = ph.hamlet.hamletId and ph.panchayat.panchayatId in( :panchayatIds) and uvd.voter.voterId = bpv.voter.voterId and  bpv.booth.publicationDate.publicationDateId = :publicationId "+
				"  group by uvd.hamlet.hamletId");
		query.setParameterList("panchayatIds", panchayatIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHamletCount(List<Long> locationIdsList,String tempVar)
	{
		StringBuilder str = new StringBuilder();
		if(tempVar != null && tempVar.equalsIgnoreCase("mandalHamlets"))
		 str.append(" select model.panchayat.tehsil.tehsilId, ");
		else if(tempVar != null && tempVar.equalsIgnoreCase("panchayatHamlets"))
		 str.append(" select model.panchayat.panchayatId, ");
		
		str.append(" count(distinct model.hamlet.hamletId) from PanchayatHamlet model where ");
		
		if(tempVar != null && tempVar.equalsIgnoreCase("mandalHamlets"))
		 str.append(" model.panchayat.tehsil.tehsilId in(:locationIdsList) group by model.panchayat.tehsil.tehsilId ");
		else if(tempVar != null && tempVar.equalsIgnoreCase("panchayatHamlets"))
			str.append(" model.panchayat.panchayatId in(:locationIdsList) group by model.panchayat.panchayatId ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("locationIdsList", locationIdsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> gethamletsInAState(Long stateId)
	{
		Query query = getSession().createQuery("select model.hamlet.hamletId, model.panchayat.panchayatId from PanchayatHamlet model where model.hamlet.township.tehsil.district.state.stateId = :stateId ");
		query.setParameter("stateId",stateId);
		return query.list();
	}
}
