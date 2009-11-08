/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 11, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.columns.enums.NominationColumnNames;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Nomination;

/**
*@author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
*@author <a href="mailto:sriharigopalnalam@gmail.com">Srihari</a>
*/

public class NominationDAO extends GenericDaoHibernate<Nomination, Long> implements
		INominationDAO {
    
	
	public NominationDAO() {
		super(Nomination.class);
	}	 
	public List<Nomination> findByAssets(Object assets) {
		
		return findByProperty(NominationColumnNames.ASSETS, assets);
	}

	public List<Nomination> findByCriminalCharges(Object criminalCharges) {
		
		return findByProperty(NominationColumnNames.CRIMINAL_CHARGES, criminalCharges);
	}

	public List<Nomination> findByLiabilities(Object liabilities) {
		
		return findByProperty(NominationColumnNames.LIABILITIES, liabilities);
	}

	public List<Nomination> findByNominationStatus(Object nominationStatus) {
		
		return findByProperty(NominationColumnNames.NOMINATION_STATUS, nominationStatus);
	}

	@SuppressWarnings("unchecked")
	public List<Nomination> findByProperty(NominationColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from Nomination where " + propertyName.getValue() + "=?", value);		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByConstituencyElection(Long constituencyElectionID){
		return getHibernateTemplate().find( "from Nomination model where model.constituencyElection.constiElecId = ?",constituencyElectionID);
	}
	
	@SuppressWarnings("unchecked")
	public Nomination findByConstituencyElectionAndCandidate(String candidateName, Long constituencyElectionID){
		Nomination nomination = null;
		Object[] params = {candidateName, constituencyElectionID};
		List<Nomination> list = getHibernateTemplate().find( "from Nomination model where model.candidate.lastname = ? and model.constituencyElection.constiElecId = ?",params);
		if(list != null && list.size() > 0)
			nomination = list.get(0);
		return nomination;
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findConstitueniesByPartyAndElectionType(Long partyId, Long electionTypeId, String electionYear){
		Object[] params = {partyId, electionTypeId, electionYear};
		return getHibernateTemplate().find( "select model.constituencyElection.constituency from Nomination model where model.party.partyId = ? and model.constituencyElection.election.electionScope.electionType.electionTypeId = ? and model.constituencyElection.election.electionYear = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByConstituencyPartyAndElectionYear(Long partyId, Long constituencyId, String electionYear){
		Object[] params = {partyId, electionYear, constituencyId};
		return getHibernateTemplate().find( "from Nomination model where model.party.partyId = ? and model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.constituencyId = ?)", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByConstituencyAndElectionYear(Long constituencyId, String electionYear){
		Object[] params = {electionYear, constituencyId};
		return getHibernateTemplate().find( "from Nomination model where model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.constituencyId = ?)", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndPartys(Long electionId,List<Long> partys){
		
		StringBuffer queryBuffer = new StringBuffer("from Nomination model where model.nominationId in(select model.nominationId from model where model.constituencyElection.election.electionId = " + electionId + " and model.party.partyId in(");
		for(int i=0;i<partys.size();i++){
			queryBuffer.append(partys.get(i) + ",");
		}
		String query = queryBuffer.toString().substring(0, queryBuffer.toString().length()-1);
		query = query + "))";
				
		return getHibernateTemplate().find(query);
	}
}
