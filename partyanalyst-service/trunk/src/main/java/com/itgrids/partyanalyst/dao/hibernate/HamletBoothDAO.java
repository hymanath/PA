package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHamletBoothDAO;
import com.itgrids.partyanalyst.model.HamletBooth;

public class HamletBoothDAO extends GenericDaoHibernate<HamletBooth, Long> implements IHamletBoothDAO{

	public HamletBoothDAO() {
		super(HamletBooth.class);
	}
	
	public Integer deleteHamletBoothsByBoothIdsList(List<Long> boothIdsList)
	{
	  Query query = getSession().createQuery(" delete from HamletBooth model where model.booth.boothId in (:boothIdsList) ");
	  query.setParameterList("boothIdsList", boothIdsList);
	  return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersCountForHamletBooth(Long userId,Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery(" select count(distinct model.voter.voterId),model.voter.gender,model3.hamletBoothId from BoothPublicationVoter model," +
				" UserVoterDetails model2,HamletBooth model3 where model3.booth.boothId = model.booth.boothId and model3.hamlet.hamletId = model2.hamlet.hamletId and model2.user.userId =:userId " +
				" and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId group by model3.hamletBoothId,model.voter.gender ");
		
		query.setParameter("userId", userId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFamiliesCountByHamletBoothIdsList(Long userId,Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select count(distinct model.voter.houseNo),model3.hamletBoothId from BoothPublicationVoter model, " +
				" UserVoterDetails model2,HamletBooth model3 where model3.booth.boothId = model.booth.boothId and model3.hamlet.hamletId = model2.hamlet.hamletId and model2.user.userId =:userId " +
				" and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId group by model3.hamletBoothId ");
		
		query.setParameter("userId", userId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAgeWiseHamletBoothList(Long userId,Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select count(distinct model.voter.voterId),model.voter.gender,model3.hamletBoothId from BoothPublicationVoter model, ");
	  str.append(" UserVoterDetails model2, HamletBooth model3 where model3.booth.boothId = model.booth.boothId and model3.hamlet.hamletId = model2.hamlet.hamletId " +
	  		" and model2.user.userId =:userId and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and ");
	  if(ageFrom != null && ageTo != null)
		str.append(" model.voter.age between :ageFrom and :ageTo ");
	  else if(ageTo == null)
		str.append(" model.voter.age > :ageFrom ");
	  
	  str.append(" group by model3.hamletBoothId,model.voter.gender ");
	  
	  Query query = getSession().createQuery(str.toString());
	  
	   query.setParameter("userId", userId);
	   query.setParameter("constituencyId", constituencyId);
	   query.setParameter("publicationDateId", publicationDateId);
	   query.setParameter("ageFrom", ageFrom);
	   if(ageTo != null)
		query.setParameter("ageTo", ageTo);   
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterFamilyInfoForHamletBooth(Long userId,Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery(" select count(model.voter.houseNo),model3.hamletBoothId from BoothPublicationVoter model," +
				" UserVoterDetails model2, HamletBooth model3 where model3.booth.boothId = model.booth.boothId and model3.hamlet.hamletId = model2.hamlet.hamletId " +
				" and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId " +
				" and model2.user.userId =:userId group by model3.hamletBoothId,model.voter.houseNo ");
		
		query.setParameter("userId", userId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteWiseVoterDetailsForHamletBooth(Long constituencyId,Long publicationDateId,Long userId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.voter.gender,count(model.voter.voterId),model2.casteState.casteStateId,model2.casteState.casteCategoryGroup.casteCategory.categoryName,model3.hamletBoothId" +
				" from BoothPublicationVoter model,UserVoterDetails model2,HamletBooth model3 where model3.booth.boothId = model.booth.boothId and model3.hamlet.hamletId = model2.hamlet.hamletId and " +
				" model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and model3.user.userId = :userId group by model2.casteState.caste.casteId,model.voter.gender ");
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		return query.list();
	}
}
