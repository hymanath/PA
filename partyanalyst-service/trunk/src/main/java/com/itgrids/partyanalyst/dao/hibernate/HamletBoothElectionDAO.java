package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.model.HamletBoothElection;

public class HamletBoothElectionDAO extends GenericDaoHibernate<HamletBoothElection, Long> implements IHamletBoothElectionDAO{

	public HamletBoothElectionDAO(){
		super(HamletBoothElection.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> findByHamletAndBoothConstituencyElection(Long hamletId,
			Long boothConstituencyElectionId) {
		Object[] params = {hamletId, boothConstituencyElectionId};	
		return getHibernateTemplate().find("select model.hamletBoothElectionId from HamletBoothElection model where " +
				"model.hamlet.hamletId = ? and model.boothConstituencyElection.boothConstituencyElectionId = ?",params);
	}
	
	public List findPanchayathsWiseBoothsAndHamletsDataInTehsilForElection(Long tehsilId, Long electionId){
		Object[] params = {tehsilId, electionId};
		return getHibernateTemplate().find("select model.hamlet.panchayatName, model.boothConstituencyElection.booth.totalVoters," +
				"model.boothConstituencyElection.boothResult.validVotes,  model.boothConstituencyElection.booth.boothId, " +
				"model.boothConstituencyElection.booth.partNo, model.hamlet.hamletId, model.hamlet.hamletName " +
				"from HamletBoothElection model where model.hamlet.township.tehsil.tehsilId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionId = ? " +
				"order by model.hamlet.panchayatName, model.boothConstituencyElection.booth.partNo",params); 
	}
	
	public List findPanchayathBoothIdsInTehsilForElection(Long tehsilId, Long electionId){
		Object[] params = {tehsilId, electionId};
		return getHibernateTemplate().find("select model.hamlet.panchayatName, model.boothConstituencyElection.boothConstituencyElectionId " +
				"from HamletBoothElection model where model.hamlet.township.tehsil.tehsilId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionId = ? group by " +
				"model.boothConstituencyElection.booth.boothId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatWiseBoothDetails(Long tehsilId,Long electionId)
	{
		Object[] params = {tehsilId, electionId};
		
		return getHibernateTemplate().find("select model.boothConstituencyElection.constituencyElection.constituency.constituencyId,model.boothConstituencyElection.constituencyElection.constituency.name," +
				" model4.panchayat.panchayatId,model4.panchayat.panchayatName,model.hamlet.hamletId,model.hamlet.hamletName,model.boothConstituencyElection.booth.boothId,model.boothConstituencyElection.booth.partNo," +
				" model.boothConstituencyElection.booth.totalVoters,model.boothConstituencyElection.boothResult.validVotes from HamletBoothElection model,PanchayatHamlet model4 where model4.hamlet.hamletId = model.hamlet.hamletId " +
				" and model.hamlet.hamletId in(select model2.hamlet.hamletId from PanchayatHamlet model2 where model2.panchayat.panchayatId in(select model3.panchayatId from Panchayat model3 where model3.tehsil.tehsilId = ?)) " +
				" and model.boothConstituencyElection.constituencyElection.election.electionId = ? group by model.boothConstituencyElection.booth.boothId order by model.boothConstituencyElection.constituencyElection.constituency.name," +
				" model4.panchayat.panchayatName,model.hamlet.hamletName, model.boothConstituencyElection.booth.boothId ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findPanchayatWiseVotingTrendsForATehsil(Long tehsilId, String electionIds)
	{	Object[] params = {tehsilId, tehsilId};
		return getHibernateTemplate().find("select model2.panchayat.panchayatId, model2.panchayat.panchayatName, " +
				" sum(model.boothConstituencyElection.boothResult.validVotes), model.boothConstituencyElection.constituencyElection.election.electionId" +
				" from HamletBoothElection model,PanchayatHamlet model2 where model.boothConstituencyElection.booth.tehsil.tehsilId = ? and model2.panchayat.tehsil.tehsilId = ? " +
				" and model.boothConstituencyElection.constituencyElection.election.electionId in ("+electionIds+
				" ) and model2.hamlet.hamletId = model.hamlet.hamletId group by model2.panchayat.panchayatId,model.boothConstituencyElection.constituencyElection.election.electionId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersCountInAPanchayat(Long electionId,Long panchayatId){
		Object[] params = {electionId, panchayatId};
		return getHibernateTemplate().find("select sum(model2.maleVoters),sum(model2.femaleVoters),sum(model2.totalVoters),count(*) from Booth model2 where model2.boothId in (select distinct model.boothConstituencyElection.booth.boothId from HamletBoothElection model " +
		" ,PanchayatHamlet model1 where model.boothConstituencyElection.constituencyElection.election.electionId = ? and model.hamlet.hamletId = model1.hamlet.hamletId and model1.panchayat.panchayatId = ? ) ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getBoothIdsByPanchayatId(Long PanchayatId, Long electionId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select distinct(model.boothConstituencyElection.booth.boothId) from HamletBoothElection model , ");
		stringBuilder.append(" PanchayatHamlet model2 where model.hamlet.hamletId = model2.hamlet.hamletId ");
		stringBuilder.append(" and model.boothConstituencyElection.constituencyElection.election.electionId = :electionId ");
		stringBuilder.append("and model2.panchayat.panchayatId =:panchayatId ");
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		queryObj.setParameter("electionId", electionId);
		queryObj.setParameter("panchayatId", PanchayatId);
		return queryObj.list();
	}
	
	public List<Object[]> getBoothsInAPanchayat(Long electionId,Long panchayatId)
	{	Object[] params = {panchayatId, electionId};
		return getHibernateTemplate().find("select distinct model.boothConstituencyElection.booth.boothId, model.boothConstituencyElection.booth.partNo  " +
				" from HamletBoothElection model,PanchayatHamlet model2 where  model2.panchayat.panchayatId = ? " +
				" and model.boothConstituencyElection.constituencyElection.election.electionId = ? and model2.hamlet.hamletId = model.hamlet.hamletId ",params);
	}
	
	public List<Object[]> getPanchayatByBoothElec(Long electionId,String partNo,Long constituencyId)
	{	Object[] params = {electionId,partNo,constituencyId};
		return getHibernateTemplate().find("select distinct model2.panchayat.panchayatId,model2.panchayat.panchayatName from HamletBoothElection model,PanchayatHamlet model2  " +
				"  where model.boothConstituencyElection.constituencyElection.election.electionId = ? and model.boothConstituencyElection.booth.partNo = ? " +
				"  and model2.hamlet.hamletId = model.hamlet.hamletId and model.boothConstituencyElection.constituencyElection.constituency.constituencyId =?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findElectionsHappendInAPanchayat(Long panchayatId, String electionType)
	{
		Query query = getSession().createQuery("select distinct(HBE.boothConstituencyElection.constituencyElection.election.electionId),HBE.boothConstituencyElection.constituencyElection.election.elecSubtype from HamletBoothElection HBE, PanchayatHamlet PH where " +
				" HBE.hamlet.hamletId = PH.hamlet.hamletId and PH.panchayat.panchayatId = :panchayatId and HBE.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = :electionType order by HBE.boothConstituencyElection.constituencyElection.election.electionDate desc ");
		query.setParameter("electionType", electionType);
		query.setParameter("panchayatId",panchayatId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllElectionsHappendInAPanchayat(Long panchayatId)
	{
		Query query = getSession().createQuery("select distinct(HBE.boothConstituencyElection.constituencyElection.election.electionId),HBE.boothConstituencyElection.constituencyElection.election.elecSubtype from HamletBoothElection HBE, PanchayatHamlet PH where " +
				" HBE.hamlet.hamletId = PH.hamlet.hamletId and PH.panchayat.panchayatId = :panchayatId and HBE.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2)  order by HBE.boothConstituencyElection.constituencyElection.election.electionDate desc ");
		query.setParameter("panchayatId",panchayatId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllElectionsHappendInAMandal(Long mandalId,List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select distinct(HBE.boothConstituencyElection.constituencyElection.election.electionId), HBE.boothConstituencyElection.constituencyElection.election.elecSubtype from HamletBoothElection HBE,  PanchayatHamlet PH where " +
				" HBE.hamlet.hamletId = PH.hamlet.hamletId and PH.panchayat.tehsil.tehsilId = :tehsilId and HBE.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2) and HBE.boothConstituencyElection.constituencyElection.constituency.constituencyId in(:constituencyIds) " +
				" order by HBE.boothConstituencyElection.constituencyElection.election.electionDate desc ");
		
		query.setParameter("tehsilId", mandalId);
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getBoothIdsByMandalId(Long mandalId, Long electionId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select distinct(model.boothConstituencyElection.booth.boothId) from HamletBoothElection model , ");
		stringBuilder.append(" PanchayatHamlet model2 where model.hamlet.hamletId = model2.hamlet.hamletId ");
		stringBuilder.append(" and model.boothConstituencyElection.constituencyElection.election.electionId = :electionId ");
		stringBuilder.append("and model2.panchayat.tehsil.tehsilId = :tehsilId ");
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		queryObj.setParameter("electionId", electionId);
		queryObj.setParameter("tehsilId", mandalId);
		return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllElectionsHappendInAConstituency(Long constituencyId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("select distinct(model.election.electionId), model.election.elecSubtype from ConstituencyElection model where " +
				"  model.constituency.constituencyId = :constituencyId and model.election.electionScope.electionType.electionTypeId in(1,2)  order by model.election.electionDate desc  ");
		
		Query query = getSession().createQuery(stringBuilder.toString());
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getBoothIdsByConstituencyId(Long constituencyId, Long electionId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select distinct(model.boothConstituencyElection.booth.boothId) from HamletBoothElection model , ");
		stringBuilder.append(" PanchayatHamlet model2 where model.hamlet.hamletId = model2.hamlet.hamletId ");
		stringBuilder.append(" and model.boothConstituencyElection.constituencyElection.election.electionId = :electionId ");
		stringBuilder.append("and model.boothConstituencyElection.constituencyElection.constituency.constituencyId = :constituencyId ");
		
		Query query = getSession().createQuery(stringBuilder.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("electionId", electionId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllElectionsHappendInALocalElectionBody(Long localElectionBodyId)
	{
		Query query = getSession().createQuery("select distinct(model.constituencyElection.election.electionId), model.constituencyElection.election.elecSubtype from BoothConstituencyElection model where " +
				" model.booth.localBody.localElectionBodyId = :localElectionBodyId and model.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2) order by model.constituencyElection.election.electionDate desc ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		return query.list();
	}
	
	public List<Object[]> getPanchayatBoothDetailsByPanchayat(Long tehsilId,Long electionId)
	{
		Object[] params = {tehsilId, electionId};
		
		return getHibernateTemplate().find("select model4.panchayat.panchayatId,model4.panchayat.panchayatName,model.boothConstituencyElection.booth.boothId,model.boothConstituencyElection.booth.partNo," +
				" model.boothConstituencyElection.booth.totalVoters,model.boothConstituencyElection.boothResult.validVotes from HamletBoothElection model,PanchayatHamlet model4 where model4.hamlet.hamletId = model.hamlet.hamletId " +
				" and model.hamlet.hamletId in(select model2.hamlet.hamletId from PanchayatHamlet model2 where model2.panchayat.panchayatId in(select model3.panchayatId from Panchayat model3 where model3.tehsil.tehsilId = ?)) " +
				" and model.boothConstituencyElection.constituencyElection.election.electionId = ? group by model.boothConstituencyElection.booth.boothId order by model.boothConstituencyElection.constituencyElection.constituency.name," +
				" model4.panchayat.panchayatName,model.hamlet.hamletName, model.boothConstituencyElection.booth.boothId ",params);
	}	
	
	//All Elections For Panchayat
	public List findPolledVotesInAllElectionsOfPanchayat(Long panchayatId){
	return getHibernateTemplate().find("select model.boothConstituencyElection.constituencyElection.election.electionId," +
	"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, " +
	"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
	"sum(model.boothConstituencyElection.boothResult.validVotes) " +
	"from HamletBoothElection model where model.hamlet.hamletId in(select model1.hamlet.hamletId from PanchayatHamlet model1 where model1.panchayat.panchayatId = ?)group by " +
	" model.boothConstituencyElection.constituencyElection.election.electionId order by " +
	"model.boothConstituencyElection.constituencyElection.election.electionYear desc, " +
	"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType",panchayatId);
		}
				
	
	public List<Object[]> getBoothDetailsByPanchayat(Long panchayatId,Long electionId)
	{
		Object[] params = {panchayatId, electionId};
		
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.boothId,model.boothConstituencyElection.booth.partNo," +
				" model.boothConstituencyElection.booth.totalVoters,model.boothConstituencyElection.boothResult.validVotes from HamletBoothElection model where model.hamlet.hamletId " +
				" in(select model1.hamlet.hamletId from PanchayatHamlet model1 where model1.panchayat.panchayatId = ?) " +
				" and model.boothConstituencyElection.constituencyElection.election.electionId = ? group by model.boothConstituencyElection.booth.boothId order by model.boothConstituencyElection.constituencyElection.constituency.name," +
				" model.hamlet.hamletName, model.boothConstituencyElection.booth.partNo ",params);
	}	
				
		
	
	
}
