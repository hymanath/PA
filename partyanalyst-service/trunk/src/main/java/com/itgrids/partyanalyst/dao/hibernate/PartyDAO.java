package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.columns.enums.PartyColumnNames;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyDAO extends GenericDaoHibernate<Party, Long> implements IPartyDAO{
	public PartyDAO(){
		super(Party.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> findByProperty(PartyColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from Party model where model." + propertyName.getValue() + "=?", value);		
	}
	
	public List<Party> findByLongName(Object longName){
		return findByProperty(PartyColumnNames.LONG_NAME, longName);
	}

	public List<Party> findByShortName(Object shortName){
		return findByProperty(PartyColumnNames.SHORT_NAME, shortName);
	}

	public List<Party> findBySymbol(Object symbol){
		return findByProperty(PartyColumnNames.SYMBOL, symbol);
	}

	public List<Party> findByAddress(Object address){
		return findByProperty(PartyColumnNames.ADDRESS, address);
	}

	public List<Party> findByComments(Object comments){
		return findByProperty(PartyColumnNames.COMMENTS, comments);
	}


	public List<Party> findByPartyRecognization(Object partyRecognization){
		return findByProperty(PartyColumnNames.PARTY_RECOGZATION, partyRecognization);
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> findByPartyId(Long partyId){
		return getHibernateTemplate().find("from Party model where model.partyId=?", partyId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> findByShortNames(String shortNames){
		return getHibernateTemplate().find("from Party model where model.shortName in ("+shortNames+")");
	}
	
	@SuppressWarnings("unchecked")
	public List findPartyIdByShortName(String shortName){
		return getHibernateTemplate().find("select model.partyId from Party model where model.shortName = ?", shortName);
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> findStatePartyByStateId(Long stateId){
		return getHibernateTemplate().find("from Party model where model.state.stateId = ?",stateId);
	}

	@SuppressWarnings("unchecked")
	public List<Party> findStaticPartiesByRecognization(String partyType,
			String shortNames) {
		return getHibernateTemplate().find("from Party model where model.partyRecognization = ? and model.shortName in ("+shortNames+")",partyType);
	}

	@SuppressWarnings("unchecked")
	public List<Party> findStaticParties(String partyType) {
		return getHibernateTemplate().find("from Party model where model.partyRecognization = ?",partyType);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllPartyNames()
	{
		return getHibernateTemplate().find("select model.partyId,model.shortName,model.longName from Party model order by model.longName");
	}

	@SuppressWarnings("unchecked")
	public List<Party> getPartyDetails(Long partyId){
		
		return getHibernateTemplate().find("from Party model where model.partyId = ?", partyId);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getParticipatedPartiesInMandal(Long tehsilId){
		
		return getHibernateTemplate().find("select distinct model.nomination.party.partyId, model.nomination.party.shortName from CandidateBoothResult model where model.boothConstituencyElection.booth.tehsil.tehsilId =? order by model.nomination.party.shortName ",tehsilId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getParticipatedPartiesInMandalByElectionId(Long tehsilId,Long electionId){
		
		Query query = getSession().createQuery("select distinct model.nomination.party.partyId, model.nomination.party.shortName from CandidateBoothResult model where model.boothConstituencyElection.booth.tehsil.tehsilId =:tehsilId and model.boothConstituencyElection.constituencyElection.election.electionId=:electionId order by model.nomination.party.shortName ");
		query.setParameter("tehsilId", tehsilId);
		query.setParameter("electionId", electionId);
		return query.list();
	}

	public List<Long> getStaticParties(String shortNames)
	{
		return getHibernateTemplate().find("select model.partyId from Party model where model.shortName in ("+shortNames+")");
	}
	
	public Party getPartyByShortName(String shortName)
	{
		Query query = getSession().createQuery("select model from Party model where model.shortName=:shortName");
		query.setParameter("shortName", shortName);
		return (Party) query.uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> getPartyList()
	{
		return getHibernateTemplate().find(" from Party model ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyShortName()
	{
		return getHibernateTemplate().find(" select model.partyId,model.shortName from Party model ");
	}
	@SuppressWarnings("unchecked")
	public String getPartyShortNameById(Long partyId)
	{
		Query query = getSession().createQuery("select model.shortName from Party model where model.partyId =:partyId ");
		query.setParameter("partyId", partyId);
		return (String) query.uniqueResult();
	}
	
	public List<Object[]> getPartyShortNameByIds(List<Long> partyIds)
	{
		Query query = getSession().createQuery("select model.partyId,model.shortName,model.partyFlag  from Party model where model.partyId in(:partyIds) ");
		query.setParameterList("partyIds", partyIds);
		return query.list();
	}
	public List<Object[]> getPartiesListByStateId(List<Long> statesList)
	{
		
		Query  query = getSession().createQuery(" select model.partyId,model.shortName from Party model where model.isNewsPortal = 'Y' and model.partyRecognization='NP' or model.state.stateId in(:statesList) order by model.shortName");
	    query.setParameterList("statesList", statesList);
		
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartiesList()
	{
	  Query query = getSession().createQuery(" select model.partyId,model.shortName from Party model where model.isNewsPortal = 'Y' order by model.shortName");
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartiesListForCoreDashBoard(String state)
	{
		StringBuilder str = new StringBuilder();
	   str.append(" select model.partyId,model.shortName from Party model where model.isNewsPortal = 'Y' " );
	  
		if(state !=null && state.equalsIgnoreCase("ap")){
			 str.append(" and model.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.equalsIgnoreCase("ts")){
			 str.append(" and model.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
		
		 str.append(" order by model.newsOrderNo");
		
		Query query = getSession().createQuery(str.toString());
	  		
	  		
	  return query.list();
	}
	public List<Object[]> getAllPartyNames(){
		Query query = getSession().createQuery("select model.partyId," +
				" model.shortName " +
				" from Party model " +
				" where model.state.stateId = 1 ");
		return query.list();
	}
}
